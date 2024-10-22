## Master-Slave database replication
1. Replication Setup
```
Master: mysql-m
Slave 1: mysql-s1
Slave 2: mysql-s2
```
The master-slave replication worked as expected. Data inserted into the master (mysql-m) was automatically replicated to both slaves (mysql-s1 and mysql-s2).

2. Script to Write Data Frequently

The script simulated continuous write operations on the master, and the replication successfully propagated the changes to the slaves.

3. Stopping Slave (mysql-s1)

Replication for mysql-s1 stopped, but mysql-s2 continued to receive updates from the master.
The master remained unaffected, and the second slave (mysql-s2) still received data.
After restarting mysql-s1, replication resumed from where it left off. The replication process ensured that no data was lost while the slave was down.

4. Changes on Slave Node (mysql-s1)

a. Removing the Last Column: Replication continues

b. Removing a Column from the Middle: Replication continues, but with incorrect data (with data of column `name` instead of `name2`). This happens because the MySQL replication system does not track column names—it assumes that the schema on the master and slave are identical and relies on column positions instead.

When you remove a column from a slave node's table (without modifying the master), replication can still continue, but it can lead to unexpected behavior, like incorrect data being inserted into the remaining columns.
