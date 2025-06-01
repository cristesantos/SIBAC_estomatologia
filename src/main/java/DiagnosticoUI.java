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
    private JComboBox<String> vermelhoField;
    private JCheckBox lesoesField;
    private JCheckBox tartaroField;
    private JCheckBox abscessoField;
    private JTextArea resultadoArea;

    public DiagnosticoUI() {
        setTitle("Sistema de Diagnóstico em Estomatologia");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        dorField = new JComboBox<>(new String[]{"pouca", "média", "muita"});
        vermelhoField = new JComboBox<>(new String[]{"pouco", "médio", "muito"});
        lesoesField = new JCheckBox("Lesões?");
        tartaroField = new JCheckBox("Tártaro?");
        abscessoField = new JCheckBox("Abscesso?");

        inputPanel.add(new JLabel("Dor:"));
        inputPanel.add(dorField);
        inputPanel.add(new JLabel("Vermelhidão:"));
        inputPanel.add(vermelhoField);
        inputPanel.add(lesoesField);
        inputPanel.add(new JLabel());
        inputPanel.add(tartaroField);
        inputPanel.add(new JLabel());
        inputPanel.add(abscessoField);
        inputPanel.add(new JLabel());

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
            sintomas.setVermelho((String) vermelhoField.getSelectedItem());
            sintomas.setLesoes(lesoesField.isSelected());
            sintomas.setTartaro(tartaroField.isSelected());
            sintomas.setAbscesso(abscessoField.isSelected());

            // Inserir fatos e disparar regras
            kSession.insert(sintomas);
            kSession.fireAllRules();

            // Capturar diagnósticos
            List<Object> resultados = kSession.getObjects()
                    .stream()
                    .filter(o -> o instanceof Diagnostico)
                    .collect(Collectors.toList());

            if (resultados.isEmpty()) {
                resultadoArea.setText("Nenhum diagnóstico encontrado.");
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