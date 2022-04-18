/*package com.dh.clinica.repository.impl;

import com.dh.clinica.dominio.Odontologo;
import com.dh.clinica.dominio.Paciente;
import com.dh.clinica.dominio.Turno;
import com.dh.clinica.repository.IDao;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDaoH2 implements IDao<Turno> {
    final static Logger log = Logger.getLogger(TurnoDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    // Con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta le script de SQL que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    private PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
    private OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();*/

    /*@Autowired
    private PacienteService pacienteService;
    private OdontologoService odontologoService;*/

    /*@Override
    public Turno guardar(Turno turno) {
        log.debug("Guardando un nuevo turno");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Como 1° paso debemos guardar les paciente y odontólogo del turno, ya que necesitamos los ID del paciente y odontólogo que se generarán en la base de datos para luego insertar esos ID en el campo paciente_id y odontologo_id de la tabla Turnos
            Paciente paciente = pacienteDaoH2.guardar(turno.getPaciente());
            turno.getPaciente().setId(paciente.getId());
            Odontologo odontologo = odontologoDaoH2.guardar(turno.getOdontologo());
            turno.getOdontologo().setId(odontologo.getId());

            // 2 Crear un sentencia que nos devuelva le Key (es decir, ID) desde la BD
            preparedStatement = connection.prepareStatement("INSERT INTO turnos(fecha, hora, paciente_id, odontologo_id) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            // No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1, odontologo.getId());
            preparedStatement.setDate(1, Util.utilDateToSqlDate(turno.getFecha()));
            preparedStatement.setTime(2, Util.utilLocalTimeToSqlTime(turno.getHora()));
            preparedStatement.setInt(3, turno.getPaciente().getId());
            preparedStatement.setInt(4, turno.getOdontologo().getId());

            // 3 Ejecutar un sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                turno.setId(keys.getLong(1));

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        log.debug("Buscando al turno con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Turno turno = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("SELECT id, fecha, hora, paciente_id, odontologo_id FROM turnos where id = ?");
            preparedStatement.setInt(1, id);

            // 3 Ejecutar un sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            // 4 Obtener resultados
            while (result.next()) {
                Long idTurno = result.getLong("id");
                Date fecha = result.getDate("fecha");
                LocalTime hora = Util.utilTimeToLocalTime(result.getTime("hora"));
                Paciente paciente = pacienteDaoH2.buscar(result.getInt("paciente_id"));
                Odontologo odontologo = odontologoDaoH2.buscar(result.getInt("odontologo_id"));

                turno = new Turno(idTurno, fecha, hora, paciente, odontologo);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return turno;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Borrando turno con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM turnos where id = ?");
            preparedStatement.setInt(1, id);

            // 3 Ejecutar un sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        log.debug("Buscando todos los turnos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Turno> turnos = new ArrayList<>();

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM turnos");

            // 3 Ejecutar un sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                Long idTurno = result.getLong("id");
                Date fecha = result.getDate("fecha");
                LocalTime hora = Util.utilTimeToLocalTime(result.getTime("hora"));
                Paciente paciente = pacienteDaoH2.buscar(result.getInt("paciente_id"));
                Odontologo odontologo = odontologoDaoH2.buscar(result.getInt("odontologo_id"));

                Turno turno = new Turno(idTurno, fecha, hora, paciente, odontologo);
                turnos.add(turno);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia
            preparedStatement = connection.prepareStatement("UPDATE turnos SET fecha = ?, hora = ?, paciente_id = ?, odontologo_id = ? WHERE id = ?");
            // No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setDate(1, Util.utilDateToSqlDate(turno.getFecha()));
            preparedStatement.setTime(2, Util.utilLocalTimeToSqlTime(turno.getHora()));
            preparedStatement.setInt(3, turno.getPaciente().getId());
            preparedStatement.setInt(4, turno.getOdontologo().getId());

            //3 Ejecutar un sentencia SQL
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return turno;
    }
}*/
