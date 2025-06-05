package com.exemplo.estomatologia;

import com.exemplo.estomatologia.model.Diagnostico;
import com.exemplo.estomatologia.model.Sintomas;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kc = ks.getKieClasspathContainer();
            KieSession kSession = kc.newKieSession("ksession-rules");

            Sintomas sintomas = new Sintomas();
            /*sintomas.setDor("leve");
            sintomas.setAparenciaGengiva("lisa e brilhante");
            sintomas.setLesoes(false);
            sintomas.setTartaro(false);
            sintomas.setAbscesso(false);
            sintomas.setMobilidadeDental(0);
            sintomas.setAumentoBolsa(false);
            sintomas.setProfundidadeBolsa(0);*/

            kSession.insert(sintomas);
            kSession.fireAllRules();

            List<Object> resultados = kSession.getObjects()
                    .stream()
                    .filter(o -> o instanceof Diagnostico)
                    .collect(Collectors.toList());

            for (Object d : resultados) {
                System.out.println(d);
            }

            kSession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

