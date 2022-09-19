package br.com.tiagopedroso.moonprobe.infra.converter;

/**
 * Class imported from https://github.com/supertsp/livraria-online-api
 */

import lombok.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MapperTest {

    /*--------------------------------+
    |  copyFields( SUPER, SUPER )  |
    +---------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_em_branco__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_em_branco__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_objetoB_SUPER_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(null);
        model.setNome(null);
//        model.setNascimento(null);
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_objetoB_SUPER_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*------------------------------+
    |  copyFields( SUPER, SUB )  |
    +-------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUB_em_branco__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUB_em_branco__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(null);
        model.setNome(null);
//        model.setNascimento(null);
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*------------------------------+
    |  copyFields( SUB, SUPER )  |
    +-------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUPER_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUPER_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUPER_em_branco__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUPER_em_branco__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_objetoB_SUPER_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        model.setId(10L);
        model.setNome(null);
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
//        model.setSalario(0);
        model.setGerente(null);
        model.setDataHoraCriacao(null);
        model.setAdmin(true);
        model.setHabilitado(null);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_objetoB_SUPER_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*----------------------------+
    |  copyFields( SUB, SUB )  |
    +-----------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_em_branco__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_em_branco__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome(null);
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(null);
        model.setAdmin(true);
        model.setHabilitado(null);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*----------------------------+
    |  copyFields( SUB, NEW )  |
    +-----------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_instanciado__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_objetoB_SUB_instanciado__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*----------------------------+
    |  copyFields( NEW, SUB )  |
    +-----------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_instanciado_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFields__Com_objetoA_SUB_instanciado_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*-------------------------------+
    |  copyFields( SUPER, NULL )  |
    +--------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_objetoB_SUPER_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        var mappingSuccess = Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(mappingSuccess, is(nullValue()));
    }


    /*-------------------------------+
    |  copyFields( NULL, SUPER )  |
    +--------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_null_e_objetoB_SUPER_preenchido__Entao_retornar_null() {
        //Dado
        ObjetoModelSuper model = null;
        ObjetoDtoSuper dto = new ObjetoDtoSuper();

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_null_e_objetoB_SUPER_preenchido__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        var mappingSuccess = Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(mappingSuccess, is(nullValue()));
    }


    /*-------------------------------+
    |  copyFields( NULL, NULL )  |
    +--------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_null_e_objetoB_SUPER_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSuper model = null;
        ObjetoDtoSuper dto = null;

        System.out.println("\ncopyFields__Com_objetoA_SUPER_null_e_objetoB_SUPER_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        var mappingSuccess = Mapper.copyFields(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(mappingSuccess, is(nullValue()));
    }


/* ---------------------------------------------------------------------------------------------------------------------*/


    /*-------------------------------------+
    |  copyFields( SUPER, TYPE_SUPER )  |
    +--------------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_tipo_SUPER__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_tipo_SUPER__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSuper.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_tipo_SUPER__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = null;

//        model.setId(null);
//        model.setNome(null);
//        model.setNascimento(null);
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_com_alguns_null_e_tipo_SUPER__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSuper.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*-----------------------------------+
    |  copyFields( SUPER, TYPE_SUB )  |
    +------------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_tipo_SUB__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSub dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_tipo_SUB__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSub.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUPER_parcialmente_preenchido_e_tipo_SUB__Entao_retornar_dto() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = null;

//        model.setId(null);
//        model.setNome(null);
//        model.setNascimento(null);
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_parcialmente_preenchido_e_tipo_SUB__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSuper.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*-----------------------------------+
    |  copyFields( SUB, TYPE_SUPER )  |
    +------------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_tipo_SUPER__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSuper dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_tipo_SUPER__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSuper.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_tipo_SUPER__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSuper dto = null;

        model.setId(10L);
//        model.setNome(null);
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
//        model.setSalario(0);
//        model.setGerente(null);
//        model.setDataHoraCriacao(null);
        model.setAdmin(true);
//        model.setHabilitado(null);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_com_alguns_null_e_tipo_SUPER__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSuper.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*---------------------------------+
    |  copyFields( SUB, TYPE_SUB )  |
    +----------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_preenchido_e_tipo_SUB__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFields__Com_objetoA_SUB_preenchido_e_tipo_SUB__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSub.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }

    @Test
    public void copyFields__Com_objetoA_SUB_parcialmente_preenchido_e_tipo_SUB__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = null;

        model.setId(10L);
//        model.setNome(null);
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
//        model.setSalario(0);
//        model.setGerente(null);
//        model.setDataHoraCriacao(null);
        model.setAdmin(true);
//        model.setHabilitado(null);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_parcialmente_preenchido_e_tipo_SUB__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSub.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*---------------------------------+
    |  copyFields( NEW, TYPE_SUB )  |
    +----------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUB_instanciado_e_tipo_SUB__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = null;

        System.out.println("\ncopyFields__Com_objetoA_SUB_instanciado_e_tipo_SUB__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, ObjetoDtoSub.class);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(dto.getSalario()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
        assertThat(model.getGerente(), is(dto.getGerente()));
    }


    /*-------------------------------+
    |  copyFields( SUPER, NULL )  |
    +--------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_preenchido_e_tipo_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSuper model = new ObjetoModelSuper();
        ObjetoDtoSuper dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);

        System.out.println("\ncopyFields__Com_objetoA_SUPER_preenchido_e_tipo_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, null);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(dto, is(nullValue()));
    }


    /*------------------------------+
    |  copyFields( NULL, NULL )  |
    +-------------------------------+
    */

    @Test
    public void copyFields__Com_objetoA_SUPER_null_e_tipo_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSuper model = null;
        ObjetoDtoSuper dto = null;

        System.out.println("\ncopyFields__Com_objetoA_SUPER_null_e_tipo_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFields(model, null);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(dto, is(nullValue()));
    }


/* ---------------------------------------------------------------------------------------------------------------------*/


    /*------------------------------------+
    |  copyFieldsToNullsOnly( SUB, SUB )  |
    +-------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(not(dto.getId())));
        assertThat(model.getNome(), is(not(dto.getNome())));
        assertThat(model.getNascimento(), is(not(dto.getNascimento())));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(not(dto.getGerente())));
        assertThat(model.getDataHoraCriacao(), is(not(dto.getDataHoraCriacao())));
        assertThat(model.isAdmin(), is(not(dto.isAdmin())));
    }


    /*-------------------------------------+
    |  copyFieldsToNullsOnly( SUB, SUB- )  |
    +--------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_parcialmente_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

//        dto.setId(null);
//        dto.setNome(null);
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
//        dto.setGerente(null);
//        dto.setDataHoraCriacao(null);
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_parcialmente_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(not(dto.getNascimento())));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(dto.getGerente()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(not(dto.isAdmin())));
    }


    /*------------------------------------+
    |  copyFieldsToNullsOnly( SUB, NEW )  |
    +-------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_instanciado__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_instanciado__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(dto.getGerente()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(not(dto.isAdmin())));
    }


    /*------------------------------------+
    |  copyFieldsToNullsOnly( NEW, SUB )  |
    +-------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_instanciado_e_objetoB_SUB_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_instanciado_e_objetoB_SUB_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(not(dto.getId())));
        assertThat(model.getNome(), is(not(dto.getNome())));
        assertThat(model.getNascimento(), is(not(dto.getNascimento())));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(not(dto.getGerente())));
        assertThat(model.getDataHoraCriacao(), is(not(dto.getDataHoraCriacao())));
    }


    /*------------------------------------+
    |  copyFieldsToNullsOnly( NEW, SUB- )  |
    +-------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_instanciado_e_objetoB_SUB_parcialmente_preenchido__Entao_retornar_dto() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

//        dto.setId(null);
//        dto.setNome(null);
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
//        dto.setGerente(null);
//        dto.setDataHoraCriacao(null);
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_instanciado_e_objetoB_SUB_parcialmente_preenchido__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(not(dto.getNascimento())));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(dto.getGerente()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(dto.isAdmin()));
    }


    /*-------------------------------------+
    |  copyFieldsToNullsOnly( SUB, NULL )  |
    +--------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = null;

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(dto, is(nullValue()));
    }


    /*-------------------------------------+
    |  copyFieldsToNullsOnly( NULL, SUB )  |
    +--------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_null_e_objetoB_SUB_preenchido__Entao_retornar_null() {
        //Dado
        ObjetoModelSub model = null;
        ObjetoDtoSub dto = new ObjetoDtoSub();

        dto.setId(20L);
        dto.setNome("Beatriz");
        dto.setSobrenome("Brunette");
        dto.setNascimento(LocalDate.parse("2020-02-02"));
        dto.setSalario(20_200.22);
        dto.setGerente(true);
        dto.setDataHoraCriacao(LocalDateTime.parse("2020-02-02T02:00:00"));
        dto.setUltimaModificacao(LocalDateTime.now());
        dto.setAdmin(false);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_null_e_objetoB_SUB_preenchido__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        dto = Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(dto, is(nullValue()));
    }


    /*--------------------------------------+
    |  copyFieldsToNullsOnly( NULL, NULL )  |
    +---------------------------------------+
    */

    @Test
    public void copyFieldsToNullsOnly__Com_objetoA_SUB_null_e_objetoB_SUB_null__Entao_retornar_null() {
        //Dado
        ObjetoModelSub model = null;
        ObjetoDtoSub dto = null;

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_null_e_objetoB_SUB_null__Entao_retornar_null");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(dto, is(nullValue()));
    }

    @Test
    void copyFieldsToNullsOnly__testing_list_conversion() {
        //Dado
        ObjetoModelSub model = new ObjetoModelSub();
        ObjetoDtoSub dto = new ObjetoDtoSub();

        List<ObjetoModelItem> lista = new ArrayList<>();
        lista.add(new ObjetoModelItem("Sao Paulo", "SP"));
        lista.add(new ObjetoModelItem("Minas Gerais", "MG"));

        model.setId(10L);
        model.setNome("Ana");
        model.setNascimento("2000-01-01");
        model.setEndereco("Rua Alpha 123");
        model.setSalario(10_000.15);
        model.setGerente(false);
        model.setDataHoraCriacao(LocalDateTime.now());
        model.setAdmin(true);
        model.setHabilitado(true);
        model.setLista(lista);

        System.out.println("\ncopyFieldsToNullsOnly__Com_objetoA_SUB_preenchido_e_objetoB_SUB_instanciado__Entao_retornar_dto");
        System.out.println("\n     --------- Antes --------- ");
        printObjectsSideBySide(model, dto);

        //Quando
        Mapper.copyFieldsToNullsOnly(model, dto);

        System.out.println("     --------- Depois -------- ");
        printObjectsSideBySide(model, dto);
        System.out.println("\n");

        //Então
        assertThat(model.getId(), is(dto.getId()));
        assertThat(model.getNome(), is(dto.getNome()));
        assertThat(model.getNascimento(), is(dto.getNascimento()));
        assertThat(model.getSalario(), is(not(dto.getSalario())));
        assertThat(model.getGerente(), is(dto.getGerente()));
        assertThat(model.getDataHoraCriacao(), is(dto.getDataHoraCriacao()));
        assertThat(model.isAdmin(), is(not(dto.isAdmin())));
    }



/* ---------------------------------------------------------------------------------------------------------------------*/


    /*##########################
            PRINT AUX
    ##########################*/
    private void printObjectsSideBySide(Object objectA, Object objectB) {
        String[] sideA = null;
        String[] sideB = null;

        if (objectA == null) {
            sideA = new String[0];
        }
        else {
            sideA = objectA.toString().split("\n");
        }

        if (objectB == null) {
            sideB = new String[0];
        }
        else {
            sideB = objectB.toString().split("\n");
        }

        var lengthOfLines = Math.max(sideA.length, sideB.length);
        var sideABiggestLine = 0;
        var spaceBetweenColumns = 8;

        //Equalizando tamanhos dos arrays e descobrindo a maior linha do A
        if (sideA.length < sideB.length) {
            var sideTemp = sideA;
            sideA = new String[lengthOfLines];

            for (int index = 0; index < lengthOfLines; index++) {
                if (index + 1 <= sideTemp.length) {
                    sideA[index] = sideTemp[index];
                } else {
                    sideA[index] = "";
                }

                if (sideA[index].length() > sideABiggestLine) {
                    sideABiggestLine = sideA[index].length();
                }
            }
        }
        else if (sideB.length < sideA.length) {
            var sideTemp = sideB;
            sideB = new String[lengthOfLines];

            for (int index = 0; index < lengthOfLines; index++) {
                if (index + 1 <= sideTemp.length) {
                    sideB[index] = sideTemp[index];
                } else {
                    sideB[index] = "";
                }

                if (sideA[index].length() > sideABiggestLine) {
                    sideABiggestLine = sideA[index].length();
                }
            }
        }
        else {
            for (int index = 0; index < lengthOfLines; index++) {
                if (sideA[index].length() > sideABiggestLine) {
                    sideABiggestLine = sideA[index].length();
                }
            }
        }

        sideABiggestLine += spaceBetweenColumns;

        //deixando as linhas do sideA com mesmo tamanho
        for (int line = 0; line < lengthOfLines; line++) {
            if (sideA[line].length() < sideABiggestLine) {
                var totalSpaces = sideABiggestLine - sideA[line].length();
                for (int countSpace = 0; countSpace < totalSpaces; countSpace++) {
                    sideA[line] += " ";
                }
            }
        }

        //imprimindo ambos lados
        for (int line = 0; line < lengthOfLines; line++) {
            System.out.println(
                sideA[line] + sideB[line]
            );
        }
    }

}


/*
 * TEST PLAN
    super  ->  super
    super  ->  sub
    sub    ->  super
    sub    ->  sub

    A         B          C       D
    aSuper    bSuper     aSub    bSub

    AB
    AC -
    AD

    BA -
    BC ?
    BD -

    CA -
    CB
    CD

    DA ?
    DB -
    DC ?
 */



    /*##########################
            TEST CLASSESS
    ##########################*/

@Getter
@Setter
class ObjetoModelSuper {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String endereco;
    private double salario;
    private Boolean gerente;

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setNascimento(String dateString) {
        this.nascimento = LocalDate.parse(dateString);
    }

    @Override
    public String toString() {
        return "ObjetoModelSuper {" +
                toStringAttributes() +
                "\n}";
    }

    public String toStringAttributes() {
        return "\n id: " + id +
                "\n nome: " + nome +
                "\n nascimento: " + nascimento +
                "\n endereco: " + endereco +
                "\n salario: " + salario +
                "\n gerente: " + gerente
                ;
    }
}

@Getter
@Setter
class ObjetoModelSub extends ObjetoModelSuper {

    private LocalDateTime dataHoraCriacao;
    private boolean admin;
    private Boolean habilitado;

    private List<ObjetoModelItem> lista = new ArrayList<>();

    public String getBairro() {
        if (getEndereco() != null) {
            return super.getEndereco().substring(super.getEndereco().length() - 5, super.getEndereco().length());
        }

        return null;
    }

    public void setBairro(String bairroParaAdicionar) {
        bairroParaAdicionar += "     ";
        super.setEndereco(super.getEndereco() + bairroParaAdicionar.substring(0, 5));
    }

    @Override
    public String toString() {
        return "ObjetoModelSub {" +
                super.toStringAttributes() +
                "\n dataHoraCriacao: " + dataHoraCriacao +
                "\n admin: " + admin +
                "\n habilitado: " + habilitado +
                "\n getBairro(): " + getBairro() +
                "\n lista: " + lista + "  - type: " + lista.getClass().getTypeName() +
                "\n}";
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class ObjetoModelItem {
    private String estado;
    private String uf;
}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ObjetoDtoSuper {

    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate nascimento;
    private double salario;
    private Boolean gerente;

    public ObjetoDtoSuper(Boolean gerente) {
        this.gerente = gerente;
    }

    public int getTamanhoNomeCompleto() {
        if (nome != null && sobrenome != null)
            return nome.length() + sobrenome.length();
        return 0;
    }

    public void setIdade(int idade) {
        nascimento = LocalDate.now().minusYears(idade);
    }

    public int getIdade() {
        if (nascimento != null)
            return Period.between(nascimento, LocalDate.now()).getYears();
        return 0;
    }

    @Override
    public String toString() {
        return "ObjetoDtoSuper {" +
                toStringAttributes() +
                "\n}";
    }

    public String toStringAttributes() {
        return "\n id: " + id +
                "\n nome: " + nome +
                "\n sobrenome: " + sobrenome +
                "\n nascimento: " + nascimento +
                "\n getTamanhoNomeCompleto(): " + getTamanhoNomeCompleto() +
                "\n getIdade(): " + getIdade() +
                "\n salario: " + salario +
                "\n gerente: " + gerente
                ;
    }

}

@Getter
@Setter
class ObjetoDtoSub extends ObjetoDtoSuper {

    private LocalDateTime dataHoraCriacao;
    private LocalDateTime ultimaModificacao;
    private boolean admin;

    private List<ObjetoDtoItem> lista;

    @Override
    public String toString() {
        return "ObjetoDtoSub {" +
                super.toStringAttributes() +
                "\n dataHoraCriacao: " + dataHoraCriacao +
                "\n ultimaModificacao: " + ultimaModificacao +
                "\n admin: " + admin +
                "\n lista: " + lista +
                "\n}";
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class ObjetoDtoItem {
    private String estado;
    private String uf;
    private int idade;
}