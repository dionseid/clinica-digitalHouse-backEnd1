/*package com.dh.clinica.repository.impl;


import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Domicilio;
import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.util.Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente>  {
    final static Logger log = Logger.getLogger(PacienteDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    private DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

    @Override
    public Paciente guardar(Paciente paciente) {
        log.debug("Guardando un nueve paciente");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Como 1° paso debemos guardar el domicilio del paciente, ya que necesitamos el ID del domicilio que se generará en la base de datos para luego insertar ese ID en el campo domicilio_id de la tabla Pacientes
            log.debug("Domicilio por agregar al paciente: " + paciente.getDomicilio());
            Domicilio domicilio = domicilioDaoH2.guardar(paciente.getDomicilio());
            paciente.getDomicilio().setId(domicilio.getId());
            log.debug("ID del domicilio: " + paciente.getDomicilio().getId());

            // 2 Crear un sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva ese Key (es decir, ID)
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nombre, apellido, email, dni, fecha_ingreso, domicilio_id) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            // No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setInt(4, paciente.getDni());
            // Hay que convertir le Date en sql.Date ya que son dos clases diferentes en Java
            preparedStatement.setDate(5, Util.utilDateToSqlDate(paciente.getFechaIngreso()));
            // Tenemos que pasarle la clave foranea del ID del domicilio, es decir, el campo domicilio_id
            preparedStatement.setInt(6, paciente.getDomicilio().getId());

            // 3 Ejecutar un sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));

            log.debug("ID del paciente: " + paciente.getId());
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Borrando paciente con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar un sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }


    }

    @Override
    public Paciente buscar(Integer id) {
        log.debug("Buscando al paciente con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("SELECT id, nombre, apellido, email, dni, fecha_ingreso, domicilio_id FROM pacientes where id = ?");
            preparedStatement.setInt(1, id);

            // 3 Ejecutar un sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            // 4 Obtener resultados
            while (result.next()) {
                int idPaciente = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date fechaIngreso = result.getDate("fecha_ingreso");
                // Con le domicilio_id traemos el domicilio de la tabla Domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDaoH2.buscar(result.getInt("domicilio_id"));
                paciente = new Paciente(idPaciente, nombre, apellido, email, dni, fechaIngreso, domicilio);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        log.debug("Buscando todes les pacientes");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM pacientes");

            // 3 Ejecutar un sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            // 4 Obtener resultados
            while (result.next()) {
                int idPaciente = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String email = result.getString("email");
                Integer dni = result.getInt("dni");
                Date fechaIngreso = result.getDate("fecha_ingreso");
                // Con le domicilio_id traemos el domicilio de la tabla Domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDaoH2.buscar(result.getInt("domicilio_id"));

                Paciente paciente = new Paciente(idPaciente, nombre, apellido, email, dni, fechaIngreso, domicilio);
                pacientes.add(paciente);
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return pacientes;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //Como primer paso actualizamos el domicilio del paciente
            Domicilio domicilio = domicilioDaoH2.actualizar(paciente.getDomicilio());

            // 2 Crear un sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva ese Key, es decir, ID
            preparedStatement = connection.prepareStatement("UPDATE pacientes SET nombre = ?, apellido = ?, email = ?, dni = ?, fecha_ingreso = ?, domicilio_id = ? WHERE id = ?");
            // No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1, paciente.getId());
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setInt(4, paciente.getDni());
            // Hay que convertir le Date en sql.Date ya que son dos clases diferentes en Java
            preparedStatement.setDate(5, Util.utilDateToSqlDate(paciente.getFechaIngreso()));
            // Tenemos que pasarle la clave foranea del ID del domicilio, es decir, el campo domicilio_id
            preparedStatement.setInt(6, paciente.getDomicilio().getId());
            //preparedStatement.setInt(7, paciente.getId());

            // 3 Ejecutar un sentencia SQL
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return paciente;
    }
}*/