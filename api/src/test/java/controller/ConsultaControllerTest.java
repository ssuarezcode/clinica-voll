package controller;

import med.voll.api.controller.ConsultaController;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ConsultaController.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleConsultaJacksonTester;

    @MockBean
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    //Sirvio!
    void agendarEscenario1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/consultas")
                        .with(csrf()) // Incluir el token CSRF en la solicitud
                )
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @DisplayName("deberia retornar estado http 200 cuando los datos ingresados son validos")
//    @WithMockUser
//    void agendarEscenario2() throws Exception {
//        var fecha = LocalDateTime.now().plusHours(1);
//        var especialidad = Especialidad.CARDIOLOGIA;
//        var datos = new DatosDetalleConsulta(null, 5L, 7L, fecha);
//
//        when(agendaDeConsultaService.agendar(any())).thenReturn(datos);
//
//        var response = mvc.perform(MockMvcRequestBuilders.post("/consultas")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(agendarConsultaJacksonTester.write(new DatosAgendarConsulta(5L, 7L, fecha, especialidad)).getJson())
//                        .with(csrf()) // Incluir el token CSRF en la solicitud
//                )
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//
//        assertThat(response.getContentAsString()).isEqualTo(detalleConsultaJacksonTester.write(datos).getJson());
//    }
}

