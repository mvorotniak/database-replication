CHANGE MASTER TO MASTER_HOST='mysql-m', MASTER_USER='replicator', MASTER_PASSWORD='replpass', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=0;
START SLAVE;
