package report;

import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {

    public void exibir(Connection con, String nome) {
        exibir(con, nome, null);
    }

    public void exibir(Connection con, String nome, HashMap param) {
        try {
            //carregar o layout do relatório
            JasperDesign jd = JRXmlLoader.load(getClass()
                    .getResourceAsStream("/report/" + nome));

            //compilar o relatório JRXML --> Jasper
            JasperReport jr = JasperCompileManager.compileReport(jd);

            //preencher o relatório com os dados
            JasperPrint jp = JasperFillManager.fillReport(jr, param, con);

            //visualizar relatório
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

            //exportar relatório
            JasperExportManager.exportReportToPdfFile(jp, "relatorio.pdf");
            JasperExportManager.exportReportToHtmlFile(jp, "relatorio.html");

            //exportar para TXT
            JRTextExporter txt = new JRTextExporter();
            txt.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            txt.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, "\f");
            txt.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Float(798));
            txt.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Float(581));
            txt.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(7));
            txt.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(14));
            txt.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "relatorio.txt");
            txt.exportReport();

        } catch (Exception e) {
            System.out.println("ERRO (relatório): " + e.getMessage());
        }
    }
}
