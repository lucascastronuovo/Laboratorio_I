package gui;

import entidades.Estudiante;
import service.EstudianteService;
import service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioEstudiante extends JPanel{


    //Componentes
    private JPanel panelComponentes;
    JLabel jlabelId;
    JLabel jlabelNombre;
    JLabel jlabelApellido;
    JTextField jTextFieldId;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;

    //Componentes de la botonera
    private JPanel panelBotonera;
    JButton jButtonGuardar;
    JButton jButtonCancelar;

    private boolean modificacion;

    private JFrame frame;
    private PanelListaEstudiantes panel;


    public void setFrame(JFrame frame){
        this.frame = frame;
    }

    public void setPanel(PanelListaEstudiantes panel){
        this.panel = panel;
    }

    //Hacemos un armarPanelFormularioEstudiante que reciba un estudiante
    //para permitir llenar todos los componentes con los datos de un estudiante
    //para moder modificarlo
    public void armarPanelFormularioEstudiante(Estudiante estudiante){
        armarPanelFormularioEstudiante();
        jTextFieldId.setText(String.valueOf(estudiante.getId()));
        jTextFieldNombre.setText(estudiante.getNombre());
        jTextFieldApellido.setText(estudiante.getApellido());
        modificacion = true;
    }

    public void armarPanelFormularioEstudiante()
    {
        jlabelId = new JLabel("ID: ");
        jlabelNombre = new JLabel("Nombre: ");
        jlabelApellido = new JLabel("Apellido: ");
        jTextFieldId = new JTextField(10);
        jTextFieldNombre = new JTextField(40);
        jTextFieldApellido = new JTextField(40);

        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(3,2));
        panelComponentes.add(jlabelId);
        panelComponentes.add(jTextFieldId);
        panelComponentes.add(jlabelNombre);
        panelComponentes.add(jTextFieldNombre);
        panelComponentes.add(jlabelApellido);
        panelComponentes.add(jTextFieldApellido);

        setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);

        panelBotonera = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonCancelar = new JButton("Cancelar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);

        //Guardamos un estudiante
        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Estudiante estudiante = new Estudiante();
                estudiante.setId( Integer.parseInt(jTextFieldId.getText()));
                estudiante.setNombre( jTextFieldNombre.getText() );
                estudiante.setApellido( jTextFieldApellido.getText() );

                EstudianteService estudianteService = new EstudianteService();

                try {

                    if(modificacion)
                        estudianteService.modificarEstudiante(estudiante);
                    else
                        estudianteService.guardarEstudiante(estudiante);

                } catch (ServiceException serviceException) {
                    //Dialog
                    JOptionPane.showMessageDialog(null,"Ha sucedido un error al guardar o modificar un estudiante. Por favor contactar al administrador: " + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                //para salir de la pantalla donde esta este panel
                frame.setVisible(false);
                //para refrezcar el panel del listado
                panel.refrezcarListado();

            }
        });

        //Al cancelar cerramos el frame donde esta el panel
        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });



    }
}
