package gui;

import entidades.Estudiante;
import service.EstudianteService;
import service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListaEstudiantes extends JPanel {


    //Grilla, Listado
    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;

    //Botonera
    JButton jButtonEliminar;
    JButton jButtonModificar;
    JButton jButtonNuevo;
    private JPanel panelBotonera;

    public void armarPanelListadoEstudiantes(){

        setLayout(new BorderLayout());

        //Grilla
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtable);

        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("NOMBRE");
        contenidoTabla.addColumn("APELLIDO");

        EstudianteService estudianteService = new EstudianteService();

        ArrayList estudiantes = null;
        try {
             estudiantes = estudianteService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
            //Dialog
            JOptionPane.showMessageDialog(null,"Ha sucedido un error al traer los estudiante. Por favor contactar al administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        for(Object obj:estudiantes){
            Estudiante estudiante = (Estudiante) obj;
            Object [] row = new Object[3];
            row[0] = estudiante.getId();
            row[1] = estudiante.getNombre();
            row[2] = estudiante.getApellido();

            contenidoTabla.addRow(row);
        }

        //Botonera
        panelBotonera = new JPanel();
        jButtonEliminar = new JButton("Eliminar");
        jButtonModificar = new JButton("Modificar");
        jButtonNuevo = new JButton("Nuevo");
        panelBotonera.add(jButtonEliminar);
        panelBotonera.add(jButtonModificar);
        panelBotonera.add(jButtonNuevo);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);



        //Cuando hacemos clic sobre el boton eliminar buscamos en la grilla (table) el id de la
        //fila seleccioanda y a traves de un objeto service eliminamos al estudiante
        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstudianteService estudianteService1 = new EstudianteService();

                int idEliminar = (int) jtable.getValueAt(jtable.getSelectedRow(), 0);

                try {
                    estudianteService1.eliminarEstudiante(idEliminar);
                    refrezcarListado();

                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al eliminar un estudiante. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }});

        //Cuando hacemos clic sobre el boton nuevo creamos una ventana es decir un frame
        //y le colocamos adentro el panel del formulario de estudiante para poder crear un nuevo
        //estudiante
        PanelListaEstudiantes panel = this;
        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame jFrame = new JFrame();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                PanelFormularioEstudiante panelFormularioEstudiante = new PanelFormularioEstudiante();
                panelFormularioEstudiante.armarPanelFormularioEstudiante();

                panelFormularioEstudiante.setFrame(jFrame);
                panelFormularioEstudiante.setPanel(panel);

                jFrame.getContentPane().add(panelFormularioEstudiante);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });

        //Con el id de la fila que seleccionamos para modificar a ese estudiante
        //traemos al estudiante de la base de datos para tener un objeto estudiante
        //con todos esos datos
        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                PanelFormularioEstudiante panelFormularioEstudiante = new PanelFormularioEstudiante();

                int idModificar = (int) jtable.getValueAt(jtable.getSelectedRow(), 0);
                EstudianteService estudianteService1 = new EstudianteService();

                Estudiante estudiante = null;
                try {
                    //lo buscamos
                    estudiante = estudianteService1.buscarEstudiante(idModificar);
                } catch (ServiceException serviceException) {
                    serviceException.printStackTrace();
                    //Dialog
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al traer un estudiante para modificarlo. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
                //se lo pasamos al formulario para que el formulario se arme pero a diferencia de
                // cuando creamos un estudiante nuevo en este caso con los
                //datos del estudiante que queremos modificar
                panelFormularioEstudiante.armarPanelFormularioEstudiante(estudiante);
                panelFormularioEstudiante.setFrame(jFrame);
                panelFormularioEstudiante.setPanel(panel);
                jFrame.getContentPane().add(panelFormularioEstudiante);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });
    }

    public void refrezcarListado()
    {
        removeAll();
        armarPanelListadoEstudiantes();
        validate();
        repaint();
    }
}
