/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import controller.Conexao;
import controller.dao.CidadeDAO;
import controller.dao.ClientesDAO;
import java.sql.Connection;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import report.Relatorio;

/**
 *
 * @author Arthur
 */
public class FormVisualizadorClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form FormVisualizadorClientes
     */
    public FormVisualizadorClientes() {
        initComponents();
        configurarFormulario();
        List<Object> lista = null;
        lista = new ClientesDAO().listar();
        exibirTarefas(lista);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spTabela = new javax.swing.JScrollPane();
        tabTarefas = new javax.swing.JTable();
        pnConsultar = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JFormattedTextField();
        btnrelatorio = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista de Clientes");

        tabTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF"
            }
        ));
        tabTarefas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTarefasMouseClicked(evt);
            }
        });
        spTabela.setViewportView(tabTarefas);

        pnConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar"));

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnConsultarLayout = new javax.swing.GroupLayout(pnConsultar);
        pnConsultar.setLayout(pnConsultarLayout);
        pnConsultarLayout.setHorizontalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPesquisar)
                .addContainerGap())
        );
        pnConsultarLayout.setVerticalGroup(
            pnConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnrelatorio.setText("Relatorio");
        btnrelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addComponent(pnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnrelatorio)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrelatorio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabTarefasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTarefasMouseClicked
       
    }//GEN-LAST:event_tabTarefasMouseClicked

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
       List<Object> lista = null;
        lista = new ClientesDAO().pesquisarPorNome(txtPesquisar.getText());
        exibirTarefas(lista);
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void btnrelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrelatorioActionPerformed
        Connection con= Conexao.Conectar();
        String rpt = "clientes.jrxml";
        new Relatorio().exibir(con, rpt);
    }//GEN-LAST:event_btnrelatorioActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrelatorio;
    private javax.swing.JPanel pnConsultar;
    private javax.swing.JScrollPane spTabela;
    private javax.swing.JTable tabTarefas;
    private javax.swing.JFormattedTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables

 private void configurarFormulario() {

        //DISPOSE_ON_CLOSE = FECHAR APENAS O FORM ATUAL
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        configurarTabela();
          
        
    }

    

    private void exibirTarefas(List<Object> lista){    

        
        if(lista != null){
            if(lista.size() > 0){
            configurarTabela();
            DefaultTableModel m = (DefaultTableModel)tabTarefas.getModel();
            
                for (Object obj : lista) {
                    Clientes pl = (Clientes)obj;
                    List<Object> lista1 = null;
                    lista1 = new CidadeDAO().pesquisarPorId(pl.getCidade());
                    
                    for (Object obj1 : lista1) {
                    Cidade tp = (Cidade)obj1;
                    m.addRow(new Object[]{
                        pl.getId(),
                        pl.getNome(),
                        pl.getCpf(),
                        tp.getNome()
                    });
                    }
                    
                    
                    
                
                }
                tabTarefas.setModel(m);
           
            }
        }
    }
    
    private void configurarTabela() {
        DefaultTableModel m = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        m.addColumn("Id");
        m.addColumn("Nome");
        m.addColumn("CPF");
        m.addColumn("Cidade");        
        tabTarefas.setModel(m);
        tabTarefas.getTableHeader().setReorderingAllowed(false);
    }
    
    public class UpperCaseDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            super.insertString(offs, str.toUpperCase(), a);
        }
    }
}

