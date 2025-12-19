package com.db.crud_pessoa.business;

import com.db.crud_pessoa.dto.EnderecoDTO;
import com.db.crud_pessoa.dto.PessoaRequest;
import com.db.crud_pessoa.dto.PessoaResponse;
import com.db.crud_pessoa.infrastructure.entitys.Endereco;
import com.db.crud_pessoa.infrastructure.entitys.Pessoa;
import com.db.crud_pessoa.infrastructure.repository.PessoaRepository;
import com.db.crud_pessoa.mapper.PessoaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    public PessoaResponse salvar(PessoaRequest request) {

        if (pessoaRepository.existsByCpf(request.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "CPF já cadastrado"
            );
        }

        Pessoa p = pessoaMapper.toEntity(request);
        return pessoaMapper.toResponse(pessoaRepository.save(p));
    }

    public List<PessoaResponse> listarPessoas() {//toResponse garante q mostre os endereços junto
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toResponse)
                .toList();
    }

    public List<EnderecoDTO> listarEnderecos(Long id) {
        Pessoa p = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return p.getEnderecos()
                .stream().
                map(e -> new EnderecoDTO(e.getId(), e.getPrincipal(), e.getRua(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado(), e.getCep()))
                .toList();
    }

    public void remover(Long id) {

        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Id da pessoa não encontrado");
        }
        pessoaRepository.deleteById(id);
    }

    public PessoaResponse atualizarTudo(Long id, PessoaRequest request) {

        Pessoa p = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        {
            p.setNome(request.nome());
            p.setCpf(request.cpf());
            p.setDataNascimento(request.dataNascimento());
            p.getEnderecos().clear();
            for (EnderecoDTO endereco : request.enderecos()) {
                Endereco e = new Endereco();
                e.setPrincipal(endereco.principal());
                e.setRua(endereco.rua());
                e.setNumero(endereco.numero());
                e.setBairro(endereco.bairro());
                e.setCidade(endereco.cidade());
                e.setEstado(endereco.estado());
                e.setCep(endereco.cep());
                e.setPessoa(p);
                p.getEnderecos().add(e);
            }
            return pessoaMapper.toResponse(pessoaRepository.save(p));
        }
    }

    public PessoaResponse adicionarEnderecoPorId(Long id, EnderecoDTO dto){

        Pessoa p = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Endereco endereco = pessoaMapper.toEnderecoEntity(dto, p);
        p.getEnderecos().add(endereco);

        return pessoaMapper.toResponse(pessoaRepository.save(p));
    }

    public List<EnderecoDTO> listarEnderecosPrincipais(Long pessoaId) {

        Pessoa p = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<EnderecoDTO> principais = p.getEnderecos().stream()
                .filter(e -> Boolean.TRUE.equals(e.getPrincipal()))
                .map(e -> new EnderecoDTO(
                        e.getId(),
                        e.getPrincipal(),
                        e.getRua(),
                        e.getNumero(),
                        e.getBairro(),
                        e.getCidade(),
                        e.getEstado(),
                        e.getCep()
                ))
                .toList();

        if (principais.isEmpty()) {
            throw new EntityNotFoundException("Nenhum endereço principal encontrado");
        }

        return principais;
    }

    public PessoaResponse atualizarEnderecoPorId(Long pessoaId, Long enderecoId, EnderecoDTO dto){

        Pessoa p = pessoaRepository.findById(pessoaId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        p.getEnderecos().stream()
                .filter(e -> e.getId().equals(enderecoId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        Endereco e = pessoaMapper.toEnderecoEntity(dto, p);
        p.getEnderecos().add(e);

        return pessoaMapper.toResponse(pessoaRepository.save(p));
    }

    public Integer calcularIdade(Long id) {

        Pessoa p = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        LocalDate dataHoje = LocalDate.now();
        return Period.between(p.getDataNascimento(), dataHoje).getYears();
    }
}