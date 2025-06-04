package estomatologia;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import com.exemplo.estomatologia.model.Diagnostico;
import com.exemplo.estomatologia.model.Sintomas;

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
        dorField = new JComboBox<>(new String[]{"leve", "média", "moderada", "alta", "extrema"});
        aparenciaGengivaField = new JComboBox<>(new String[]{"lisa e brilhante", "edemaciada"});
        lesoesField = new JCheckBox("Lesões?");
        tartaroField = new JCheckBox("Tártaro?");
        abscessoField = new JCheckBox("Abscesso?");
        fistulaField = new JCheckBox("Fístula?");
        sangramentoField = new JCheckBox("Sangramento?");
        aumentoBolsaField = new JCheckBox("Aumento da bolsa?");
        profundidadeField = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        mobilidadeField = new JComboBox<>(new Integer[]{0, 1, 2, 3});

        inputPanel.setLayout(new GridLayout(11, 2)); // ajuste

        inputPanel.add(new JLabel("Dor:"));
        inputPanel.add(dorField);
        inputPanel.add(new JLabel("Vermelhidão:"));
        inputPanel.add(aparenciaGengivaField);
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
        inputPanel.add(new JLabel("Profundidade da bolsa (mm):"));
        inputPanel.add(profundidadeField);
        inputPanel.add(new JLabel("Mobilidade dental:"));
        inputPanel.add(mobilidadeField);

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
            kSession.insert(sintomas);
            kSession.fireAllRules();

            // Capturar diagnósticos
            List<Object> resultados = kSession.getObjects()
                    .stream()
                    .filter(o -> o instanceof Diagnostico)
                    .collect(Collectors.toList());

            if (resultados.isEmpty()) {
                resultadoArea.setText("Encaminhar paciente para especialista.");
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
