package br.com.tiagopedroso.moonprobe.infra.converter;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.CelestialAreaRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProbeConverterTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    CelestialAreaRepository celestialAreaRepository;

    static final LocalDateTime created = LocalDateTime.now();
    static final CelestialArea celestialAreaModel = new CelestialArea(
            1L,
            "Earth Moon",
            5,
            5,
            created
    );

    static final int MODEL_ID_WITH_FK = 1;

    static List<br.com.tiagopedroso.moonprobe.logic.Probe> logicList = new ArrayList<>(4);
    static List<Probe> modelList = new ArrayList<>(4);
    static List<ProbeCreate> createList = new ArrayList<>(4);
    static List<ProbeResponse> responseList = new ArrayList<>(4);


    @BeforeAll
    static void initLogicList() {
        logicList.add(new br.com.tiagopedroso.moonprobe.logic.Probe(
                        "Sun Rover",
                        new FitCoord(1, 2, 5, 5),
                        Orientation.NORTH,
                        new CommandSequence("RM")
                )
        );

        logicList.add(new br.com.tiagopedroso.moonprobe.logic.Probe(
                        "Athena Rover",
                        new FitCoord(3, 3, 5, 5),
                        Orientation.EAST,
                        new CommandSequence("M")
                )
        );

        logicList.add(new br.com.tiagopedroso.moonprobe.logic.Probe(
                        "Ranger Rover",
                        new FitCoord(2, 1, 3, 3),
                        Orientation.SOUTH,
                        new CommandSequence("MLLMM")
                )
        );

        logicList.add(new br.com.tiagopedroso.moonprobe.logic.Probe(
                        "Flower Rover",
                        new FitCoord(20, 9, 30, 10),
                        Orientation.WEST,
                        new CommandSequence("RRM")
                )
        );
    }

    @BeforeAll
    static void initModelList() {
        final var probeWithCelestialArea1 = new Probe(
                1L,
                "Sun Rover",
                1,
                2,
                Orientation.NORTH,
                "RM",
                created,
                0,
                celestialAreaModel
        );
        celestialAreaModel.getProbes().add(probeWithCelestialArea1);

        final var probeWithCelestialArea2 = new Probe(
                2L,
                "Athena Rover",
                3,
                3,
                Orientation.EAST,
                "M",
                created,
                0,
                celestialAreaModel
        );
        celestialAreaModel.getProbes().add(probeWithCelestialArea2);

        modelList.add(probeWithCelestialArea1);
        modelList.add(probeWithCelestialArea2);

        modelList.add(new Probe(
                        3L,
                        "Ranger Rover",
                        2,
                        1,
                        Orientation.SOUTH,
                        "MLLMM",
                        created,
                        0,
                        null
                )
        );

        modelList.add(new Probe(
                        4L,
                        "Flower Rover",
                        20,
                        9,
                        Orientation.WEST,
                        "RRM",
                        created,
                0,
                        null
                )
        );
    }

    @BeforeAll
    static void initCreateList() {
        createList.add(new ProbeCreate(
                        1L,
                        "Sun Rover",
                        1,
                        2,
                        Orientation.NORTH,
                        "RM",
                        created,
                0
                )
        );

        createList.add(new ProbeCreate(
                        2L,
                        "Athena Rover",
                        3,
                        3,
                        Orientation.EAST,
                        "M",
                        created,
                0
                )
        );

        createList.add(new ProbeCreate(
                        3L,
                        "Ranger Rover",
                        2,
                        1,
                        Orientation.SOUTH,
                        "MLLMM",
                        created,
                0
                )
        );

        createList.add(new ProbeCreate(
                        4L,
                        "Flower Rover",
                        20,
                        9,
                        Orientation.WEST,
                        "RRM",
                        created,
                0
                )
        );
    }

    @BeforeAll
    static void initResponseList() {
        responseList.add(new ProbeResponse(
                        1L,
                        "Sun Rover",
                        1,
                        2,
                        Orientation.NORTH,
                        "RM",
                        created,
                        1L,
                0
                )
        );

        responseList.add(new ProbeResponse(
                        2L,
                        "Athena Rover",
                        3,
                        3,
                        Orientation.EAST,
                        "M",
                        created,
                        1L,
                0
                )
        );

        responseList.add(new ProbeResponse(
                        3L,
                        "Ranger Rover",
                        2,
                        1,
                        Orientation.SOUTH,
                        "MLLMM",
                        created,
                        null,
                0
                )
        );

        responseList.add(new ProbeResponse(
                        4L,
                        "Flower Rover",
                        20,
                        9,
                        Orientation.WEST,
                        "RRM",
                        created,
                        null,
                0
                )
        );
    }

    @Test
    void convertCreateDtoToModel() {
        //Given
        final var dtoId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var dto = createList.get(dtoId);
        final var expectedModel = modelList.get(modelId);

        //When
        final var response = ProbeConverter.convertCreateDtoToModel(dto);

        //Then
        assertEquals(expectedModel.getId(), response.getId());
        assertEquals(expectedModel.getName(), response.getName());
        assertEquals(expectedModel.getX(), response.getX());
        assertEquals(expectedModel.getY(), response.getY());
        assertEquals(expectedModel.getOrientation(), response.getOrientation());
        assertEquals(expectedModel.getCommandSequence(), response.getCommandSequence());
        assertEquals(expectedModel.getCreated(), response.getCreated());
    }

    @Test
    void convertResponseDtoToModel_with_repository() {
        //Given
        final var dtoId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var dto = responseList.get(dtoId);
        final var expectedModel = modelList.get(modelId);

        //When
        when(celestialAreaRepository.findById(1L))
                .thenReturn(Optional.of(celestialAreaModel));

        final var response = ProbeConverter.convertResponseDtoToModel(dto, celestialAreaRepository);

        //Then
        assertEquals(expectedModel.getId(), response.getId());
        assertEquals(expectedModel.getName(), response.getName());
        assertEquals(expectedModel.getX(), response.getX());
        assertEquals(expectedModel.getY(), response.getY());
        assertEquals(expectedModel.getOrientation(), response.getOrientation());
        assertEquals(expectedModel.getCommandSequence(), response.getCommandSequence());
        assertEquals(expectedModel.getCreated(), response.getCreated());

        //Not Null case
        assertNotNull(response.getCelestialArea());
    }

    @Test
    void convertResponseDtoToModel_with_celestial_model() {
        //Given
        final var dtoId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var dto = responseList.get(dtoId);
        final var expectedModel = modelList.get(modelId);

        //When
        final var response = ProbeConverter.convertResponseDtoToModel(dto, celestialAreaModel);

        //Then
        assertEquals(expectedModel.getId(), response.getId());
        assertEquals(expectedModel.getName(), response.getName());
        assertEquals(expectedModel.getX(), response.getX());
        assertEquals(expectedModel.getY(), response.getY());
        assertEquals(expectedModel.getOrientation(), response.getOrientation());
        assertEquals(expectedModel.getCommandSequence(), response.getCommandSequence());
        assertEquals(expectedModel.getCreated(), response.getCreated());

        //Not Null cases
        assertNotNull(response.getCelestialArea());
    }

    @Test
    void convertModelToCreateDto() {
        //Given
        final var dtoId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedDto = createList.get(dtoId);

        //When
        final var response = ProbeConverter.convertModelToCreateDto(model);

        //Then
        assertEquals(expectedDto.getId(), response.getId());
        assertEquals(expectedDto.getName(), response.getName());
        assertEquals(expectedDto.getX(), response.getX());
        assertEquals(expectedDto.getY(), response.getY());
        assertEquals(expectedDto.getOrientation(), response.getOrientation());
        assertEquals(expectedDto.getCommandSequence(), response.getCommandSequence());
        assertEquals(expectedDto.getCreated(), response.getCreated());
    }

    @Test
    void convertModelToResponseDto() {
        //Given
        final var dtoId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedDto = responseList.get(dtoId);

        //When
        final var response = ProbeConverter.convertModelToResponseDto(model);

        //Then
        assertEquals(expectedDto.getId(), response.getId());
        assertEquals(expectedDto.getName(), response.getName());
        assertEquals(expectedDto.getX(), response.getX());
        assertEquals(expectedDto.getY(), response.getY());
        assertEquals(expectedDto.getOrientation(), response.getOrientation());
        assertEquals(expectedDto.getCommandSequence(), response.getCommandSequence());
        assertEquals(expectedDto.getCreated(), response.getCreated());

        //Not Null cases
        assertNotNull(response.getCelestialAreaId());
    }

    @Test
    void convertCreateDtoToLogic() {
        //Given
        final var dtoId = 1;
        final var logicId = 1;

        final var dto = createList.get(dtoId);
        final var expectedLogic = logicList.get(logicId);

        //When
        final var response = ProbeConverter.convertCreateDtoToLogic(dto);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.coord.getX(), response.coord.getX());
        assertEquals(expectedLogic.coord.getY(), response.coord.getY());
        assertEquals(expectedLogic.getOrientation(), response.getOrientation());
        assertEquals(expectedLogic.getCommandSequence(), response.getCommandSequence());
    }

    @Test
    void convertResponseDtoToLogic() {
        //Given
        final var dtoId = 1;
        final var logicId = 1;

        final var dto = responseList.get(dtoId);
        final var expectedLogic = logicList.get(logicId);

        //When
        final var response = ProbeConverter.convertResponseDtoToLogic(dto);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.coord.getX(), response.coord.getX());
        assertEquals(expectedLogic.coord.getY(), response.coord.getY());
        assertEquals(expectedLogic.getOrientation(), response.getOrientation());
        assertEquals(expectedLogic.getCommandSequence(), response.getCommandSequence());
    }

    @Test
    void convertModelToLogic() {
        //Given
        final var logicId = 1;
        final var modelId = MODEL_ID_WITH_FK;

        final var model = modelList.get(modelId);
        final var expectedLogic = logicList.get(logicId);

        //When
        final var response = ProbeConverter.convertModelToLogic(model);

        //Then
        assertEquals(expectedLogic.getName(), response.getName());
        assertEquals(expectedLogic.coord.getX(), response.coord.getX());
        assertEquals(expectedLogic.coord.getY(), response.coord.getY());
        assertEquals(expectedLogic.getOrientation(), response.getOrientation());
        assertEquals(expectedLogic.getCommandSequence(), response.getCommandSequence());
    }
}