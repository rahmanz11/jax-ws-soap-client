package com.example.soapclient.client;

import com.example.soapclient.ws.ParametrosUbicaPlusDTO;
import com.example.soapclient.ws.UbicaPlusWS;
import com.example.soapclient.ws.UbicaPlusWSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BridgeClient {

    @Value("${client.user.name}")
    private String userName;

    @Value("${client.user.password}")
    private String userPassword;

    public void call() {
        ParametrosUbicaPlusDTO request = new ParametrosUbicaPlusDTO();

        com.example.soapclient.ws.String codi = new com.example.soapclient.ws.String();
        codi.setValue(java.lang.String.valueOf(5632));
        request.setCodigoInformacion(codi);
        com.example.soapclient.ws.String tipo = new com.example.soapclient.ws.String();
        tipo.setValue(java.lang.String.valueOf(1));
        request.setTipoIdentificacion(tipo);
        com.example.soapclient.ws.String motivo = new com.example.soapclient.ws.String();
        motivo.setValue(java.lang.String.valueOf(24));
        request.setMotivoConsulta(motivo);

        UbicaPlusWSService ws = new UbicaPlusWSService();
        ws.setHandlerResolver(portInfo -> {
            List<Handler> handlerList = new ArrayList<>();
            handlerList.add(new SoapHandler());
            return handlerList;
        });

        UbicaPlusWS iws = ws.getUbicaPlus();
        // Add username and password for Basic Authentication
        Map<String, Object> reqContext = ((BindingProvider)
                iws).getRequestContext();
        reqContext.put(BindingProvider.USERNAME_PROPERTY, userName);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, userPassword);

        com.example.soapclient.ws.String resp = iws.consultaUbicaPlus(request);
        System.out.println(resp.getValue());
    }
}
