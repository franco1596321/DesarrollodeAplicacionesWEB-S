package pe.edu._5.cl1_jpa_data_alejandro_franco.Confing;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConexionesConfig {


    @Value("${DB_WORLD_URL}")
    private String dbSakilaUrl;
    @Value("${DB_WORLD_USER}")
    private String dbSakilaUser;
    @Value("${DB_WORLD_PASS}")
    private String dbSakilaPass;
    @Value("${DB_WORLD_DRIVER}")
    private String dbSakilaDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        /**
         * Configurar propiedad de conexion a BD
         */
        config.setJdbcUrl(dbSakilaUrl);
        config.setUsername(dbSakilaUser);
        config.setPassword(dbSakilaPass);
        config.setDriverClassName(dbSakilaDriver);

        /**
         * Configurar propiedades del pool de HikariCP
         * - MaximumPoolSize: MÃ¡ximo # de conexiones del pool.
         * - MinimumIdle: MÃ­nimo # de conexiones inactivas del pool.
         * - IdleTimeout: Tiempo mÃ¡ximo de espera para
         *      eliminar una conexiÃ³n inactiva.
         * - ConnectionTimeout: Tiempo mÃ¡ximo de espera
         *      para conectarse a la BD.
         */
        config.setMaximumPoolSize(30);  // Máximo de conexiones en el pool
        config.setMinimumIdle(4);       // Mínimo de conexiones inactivas en el pool
        config.setIdleTimeout(240000);  // Tiempo de espera (en milisegundos) antes de cerrar conexiones inactivas (4 minutos)
        config.setConnectionTimeout(45000);  // Tiempo máximo de espera para obtener una conexión del pool (45 segundos)


        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }
}
