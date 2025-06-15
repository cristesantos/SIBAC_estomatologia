package estomatologia;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.exemplo.estomatologia.model.Diagnostico;
import com.exemplo.estomatologia.model.Sintomas;
import com.exemplo.estomatologia.model.Evidencia;
import com.exemplo.estomatologia.model.Hipotese;

public class DiagnosticoUI extends JFrame {

    private JComboBox<String> dorField;
    private JComboBox<String> aparenciaGengivaField;
    private JCheckBox lesoesField;
    private JCheckBox tartaroField;
    private JCheckBox abscessoField;
    private JTextArea resultadoArea;
    private JCheckBox fistulaField, sangramentoField, aumentoBolsaField;
    private JSpinner profundidadeField;
    private JComboBox<Integer> mobilidadeField;

    public DiagnosticoUI() {
        setTitle("Sistema de Diagnóstico em Estomatologia");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        dorField = new JComboBox<>(new String[]{"sem dor","leve", "intensa"});
        aparenciaGengivaField = new JComboBox<>(new String[]{"lisa e brilhante", "edemaciada"});
        lesoesField = new JCheckBox("Lesões?");
        tartaroField = new JCheckBox("Tártaro?");
        abscessoField = new JCheckBox("Abscesso?");
        fistulaField = new JCheckBox("Fístula?");
        sangramentoField = new JCheckBox("Sangramento?");
        aumentoBolsaField = new JCheckBox("Aumento da bolsa?");
        profundidadeField = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        mobilidadeField = new JComboBox<>(new Integer[]{0, 1, 2, 3});

        inputPanel.setLayout(new GridLayout(12, 2)); // ajuste

        inputPanel.add(new JLabel("Dor:"));
        inputPanel.add(dorField);
        inputPanel.add(new JLabel("Aparência Gengiva:"));
        inputPanel.add(aparenciaGengivaField);
        inputPanel.add(new JLabel("Profundidade da bolsa (mm):"));
        inputPanel.add(profundidadeField);
        inputPanel.add(new JLabel("Mobilidade dental:"));
        inputPanel.add(mobilidadeField);
        inputPanel.add(lesoesField);
        inputPanel.add(new JLabel());
        inputPanel.add(tartaroField);
        inputPanel.add(new JLabel());
        inputPanel.add(abscessoField);
        inputPanel.add(new JLabel());
        inputPanel.add(fistulaField);
        inputPanel.add(new JLabel());
        inputPanel.add(sangramentoField);
        inputPanel.add(new JLabel());
        inputPanel.add(aumentoBolsaField);


        JButton diagnosticarButton = new JButton("Diagnosticar");
        diagnosticarButton.addActionListener(this::executarDiagnostico);
        inputPanel.add(diagnosticarButton);

        add(inputPanel, BorderLayout.NORTH);

        // Área de resultado
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
    }

    private void executarDiagnostico(ActionEvent e) {
        resultadoArea.setText("");

        try {
            // Setup Drools
            KieServices ks = KieServices.Factory.get();
            KieContainer kc = ks.getKieClasspathContainer();
            KieSession kSession = kc.newKieSession("ksession-rules");

            // Criar sintomas com base nos inputs da UI
            Sintomas sintomas = new Sintomas();
            sintomas.setDor((String) dorField.getSelectedItem());
            sintomas.setAparenciaGengiva((String) aparenciaGengivaField.getSelectedItem());
            sintomas.setLesoes(lesoesField.isSelected());
            sintomas.setTartaro(tartaroField.isSelected());
            sintomas.setAbscesso(abscessoField.isSelected());
            sintomas.setFistula(fistulaField.isSelected());
            sintomas.setSangramento(sangramentoField.isSelected());
            sintomas.setAumentoBolsa(aumentoBolsaField.isSelected());
            sintomas.setProfundidadeBolsa((Integer) profundidadeField.getValue());
            sintomas.setMobilidadeDental((Integer) mobilidadeField.getSelectedItem());

            // Inserir fatos e disparar regras
            /*kSession.insert(sintomas);

            if (sintomas.getDor() != null) {
                double fc = sintomas.getDor().equals("alta") ? 0.9 : sintomas.getDor().equals("moderada") ? 0.7 : 0.3;
                kSession.insert(new Evidencia("dor", sintomas.getDor(), fc));
            }
            if (sintomas.isSangramento()) {
                kSession.insert(new Evidencia("sangramento", "true", 0.8));
            }*/

            kSession.insert(sintomas);

            kSession.insert(new Evidencia("dor", sintomas.getDor(), 0.0));
            if (sintomas.isSangramento())
                kSession.insert(new Evidencia("sangramento", true, 0.0));
            if (sintomas.getProfundidadeBolsa() > 5)
                kSession.insert(new Evidencia("profundidadeBolsa", "alta", 0.0));
            if (sintomas.getMobilidadeDental() > 1)
                kSession.insert(new Evidencia("mobilidadeDental", "alta", 0.0));
            if (sintomas.isFistula()) {
                kSession.insert(new Evidencia("fistula", true, 0.0));
            }
            if (sintomas.isTartaro()) {
                kSession.insert(new Evidencia("tartaro", true, 0.0));
            }
            kSession.insert(new Evidencia("aparenciaGengiva", sintomas.getAparenciaGengiva(), 0.0));
            if (sintomas.isLesoes()) {
                kSession.insert(new Evidencia("lesoes", true, 0.0));
            }
            if (sintomas.isAbscesso()) {
                kSession.insert(new Evidencia("abscesso", true, 0.0));
            }
            if (sintomas.isAumentoBolsa()) {
                kSession.insert(new Evidencia("aumentoBolsa", true, 0.0));
            }

            kSession.fireAllRules();

            // Capturar diagnósticos
            /*List<Object> resultados = kSession.getObjects()
                    .stream()
                    .filter(o -> o instanceof Diagnostico)
                    .sorted(Comparator.comparingDouble(Diagnostico::getFc))
                    .collect(Collectors.toList());*/

            List<Object> resultados = kSession.getObjects()
                    .stream()
                    .filter(o -> o instanceof Diagnostico)
                    .map(o -> (Diagnostico) o)
                    .sorted(Comparator.comparingDouble(Diagnostico::getFc).reversed())
                    .collect(Collectors.toList());


            if (resultados.isEmpty()) {
                resultadoArea.setText("Diagnóstico indefinido.");
            } else {
                for (Object d : resultados) {
                    resultadoArea.append(d.toString() + "\n");
                }
            }

            kSession.dispose();
        } catch (Throwable ex) {
            resultadoArea.setText("Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiagnosticoUI().setVisible(true));
    }
}
