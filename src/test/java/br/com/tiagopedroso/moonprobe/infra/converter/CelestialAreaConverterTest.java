package br.com.tiagopedroso.moonprobe.infra.converter;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.ProbeRepository;
import br.com.tiagopedroso.moonprobe.logic.CommandSequence;
import br.com.tiagopedroso.moonprobe.logic.FitCoord;
import br.com.tiagopedroso.moonprobe.logic.Orientation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CelestialAreaConverterTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    ProbeRepository probeRepository;

    static final LocalDateTime created = LocalDateTime.now();

    static final int MODEL_ID_WITH_FK = 0;

    static List<br.com.tiagopedroso.moonprobe.logic.CelestialArea> logicList = new ArrayList<>(2);

    static List<CelestialArea> modelList = new ArrayList<>(2);
    static List<CelestialAreaCreate> createList = new ArrayList<>(2);
    static List<CelestialAreaResponse> responseList = new ArrayList<>(2);

    @BeforeAll
    static void initLogicList() {
        logicList.add(new br.com.tiagopedroso.moonprobe.logic.CelestialArea(
                        "Earth Moon",
                        5,
                        5
                )
        );

        logicList.get(0).addProbe(
                new br.com.tiagopedroso.moonprobe.logic.Probe(
                        "Sun Rover",
                        new FitCoord(1, 2, 5, 5),
                        Orientation.NORTH,
                        new CommandSequence("RM")
                )
        );

        logicList.add(new br.com.tiagopedroso.moonprobe.logic.CelestialArea(
                        "Saturn Moon",
                        10,
                        10
                )
        );
    }

    @BeforeAll
    static void initModelList() {
        final var celestialAreaWithProbeList = new CelestialArea(
                1L,
                "Earth Moon",
                5,
                5,
                created
        );

        final var probeWithCelestialArea1 = new Probe(
                1L,
                "Sun Rover",
                1,
                2,
                Orientation.NORTH,
                "RM",
                created,
                celestialAreaWithProbeList
        );

        final List<Probe> probes = new ArrayList<>(1);
        probes.add(probeWithCelestialArea1);
        celestialAreaWithProbeList.setProbes(probes);

        modelList.add(celestialAreaWithProbeList);

        modelList.add(new CelestialArea(
                        2L,
                        "Saturn Moon",
                        10,
                        10,
                        created
                )
        );
    }

    @BeforeAll
    static void initCreateList() {
        createList.add(new CelestialAreaCreate(
                        1L,
                        "Earth Moon",
                        5,
                        5,
                        created
                )
        );
        createList.add(new CelestialAreaCreate(
                        2L,
                        "Saturn Moon",
                        10,
                        10,
                        created
                )
        );
    }

    @BeforeAll
    static void initResponseList() {
        final List<Long> probesId = new ArrayList<>(1);
        probesId.add(1L);

        responseList.add(new CelestialAreaResponse(
                        1L,
                        "Earth Moon",
                        5,
                        5,
                        created,
                        probesId
                )
        );

        responseList.add(new CelestialAreaResponse(
                        2L,
                        "Saturn Moon",
                        10,
                        10,
                        created,
                        null
                )
        );
    }

    @Test
    void convertCreateDtoToModel() {
        //Given
        final var dtoId = 0;
        final var modelId = MODEL_ID_WITH_FK;

        final var dto = createList.get(dtoId);
        final var expectedModel = modelList.get(modelId);

        //When
        final var response = CelestialAreaConverter.convertCreateDtoToModel(dto);

        //Then
        assertEquals(expectedModel.getId(), response.getId());
        assertEquals(expectedModel.getName(), response.getName());
        assertEquals(expectedModel.getWidth(), response.getWidth());
        assertEquals(expectedModel.getHeight(), response.getHeight());
        assertEquals(expectedModel.getCreated(), response.getCreated());
        assertEquals(0, response.getProbes().size());
    }

    @Test
    void convertResponseDtoToModel() {
        //Given
        final var dtoId = 0;
        final var modelId = MODEL_ID_WITH_FK;

        final var dto = responseList.get(dtoId);
        final var expectedModel = modelList.get(modelId);

        //When
        final var response = CelestialAreaConverter.convertResponseDtoToModel(dto);

        //Then
        assertEquals(expectedModel.getId(), response.getId());
        assertEquals(expectedModel.getName(), response.getName());
        assertEquals(expectedModel.getWidth(), response.getWidth());
        assertEquals(expectedModel.getHeight(), response.getHeight());
        assertEquals(expectedModel.getCreated(), response.getCreated());
        assertEquals(0, response.getProbes().size());
    }

    @Test
    void convertModelToCreateDto() {
        //Given
        final var dtoId = 0;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedDto = createList.get(dtoId);

        //When
        final var response = CelestialAreaConverter.convertModelToCreateDto(model);

        //Then
        assertEquals(expectedDto.getId(), response.getId());
        assertEquals(expectedDto.getName(), response.getName());
        assertEquals(expectedDto.getWidth(), response.getWidth());
        assertEquals(expectedDto.getHeight(), response.getHeight());
        assertEquals(expectedDto.getCreated(), response.getCreated());
    }

    @Test
    void convertModelToResponseDto() {
        //Given
        final var dtoId = 0;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedDto = responseList.get(dtoId);

        //When
        final var response = CelestialAreaConverter.convertModelToResponseDto(model);

        //Then
        assertEquals(expectedDto.getId(), response.getId());
        assertEquals(expectedDto.getName(), response.getName());
        assertEquals(expectedDto.getWidth(), response.getWidth());
        assertEquals(expectedDto.getHeight(), response.getHeight());
        assertEquals(expectedDto.getCreated(), response.getCreated());
        assertEquals(expectedDto.getProbesId().size(), response.getProbesId().size());
    }

    @Test
    void convertCreateDtoToLogic() {
        //Given
        final var dtoId = 0;
        final var logicId = 0;

        final var dto = createList.get(dtoId);
        final var expectedLogic = logicList.get(logicId);

        //When
        final var response = CelestialAreaConverter.convertCreateDtoToLogic(dto);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.getWidth(), response.getWidth());
        assertEquals(expectedLogic.getHeight(), response.getHeight());
        assertEquals(0, response.lengthOfProbes());
    }

    @Test
    void convertResponseDtoToLogic() {
        //Given
        final var dtoId = 0;
        final var logicId = 0;

        final var dto = responseList.get(dtoId);
        final var expectedLogic = logicList.get(logicId);

        //When
        when(probeRepository.findAllById( Arrays.asList(1L)))
                .thenReturn(modelList.get(MODEL_ID_WITH_FK).getProbes());

        final var response = CelestialAreaConverter.convertResponseDtoToLogic(dto, probeRepository);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.getWidth(), response.getWidth());
        assertEquals(expectedLogic.getHeight(), response.getHeight());
        assertEquals(1, response.lengthOfProbes());
    }

    @Test
    void convertModelToLogic() {
        //Given
        final var logicId = 0;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedLogic = logicList.get(logicId);

        //When
        final var response = CelestialAreaConverter.convertModelToLogic(model);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.getWidth(), response.getWidth());
        assertEquals(expectedLogic.getHeight(), response.getHeight());
        assertEquals(1, response.lengthOfProbes());
    }
}