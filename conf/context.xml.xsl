<Resource name="jdbc/joboonja" auth="Container"
          factory="com.zaxxer.hikari.HikariJNDIFactory"
          type="javax.sql.DataSource"
          minimumIdle="5"
          maximumPoolSize="10"
          connectionTimeout="300000"
          driverClassName="org.sqlite.JDBC"
          jdbcUrl="jdbc:sqlite:/Users/md/Desktop/collage/IE/CA1/IE_1/IE_7/identifier.sqlite"
          dataSource.implicitCachingEnabled="true"/>