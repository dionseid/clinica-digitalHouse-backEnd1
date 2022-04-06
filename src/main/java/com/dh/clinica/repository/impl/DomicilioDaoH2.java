/*package com.dh.clinica.repository.impl;

import com.dh.clinica.repository.IDao;
import com.dh.clinica.dominio.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio> {
    final static Logger log = Logger.getLogger(DomicilioDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    // Con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de SQL que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        log.debug("Guardando un nuevo domicilio");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1 Levantar le driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2 Crear un sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva ese Key, es decir, ID
            preparedStatement = connection.prepareStatement("INSERT INTO domicilios(calle, numero, localidad, provincia) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            // No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,domicilio.getId());
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            // 3 Ejecutar un sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                domicilio.setId(keys.getInt(1));
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Borrando domicilio con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM domicilios where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
    }

    @Override
    public Domicilio buscar(Integer id) {
        log.debug("Buscando al domicilio con ID = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Domicilio domicilio = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,calle,numero,localidad,provincia FROM domicilios where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idDomicilio = result.getInt("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");

                domicilio = new Domicilio(idDomicilio,calle,numero,localidad,provincia);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        log.debug("Buscando todos los domicilios");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Domicilio> domicilios = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM domicilios");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                int idDomicilio = result.getInt("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");

                Domicilio domicilio = new Domicilio(idDomicilio, calle, numero, localidad, provincia);

                domicilios.add(domicilio);

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return domicilios;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

             preparedStatement = connection.prepareStatement("UPDATE domicilios SET calle = ?, numero = ? ,localidad = ?, provincia = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,domicilio.getId());
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setLong(5, domicilio.getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return domicilio;
    }
}*/

