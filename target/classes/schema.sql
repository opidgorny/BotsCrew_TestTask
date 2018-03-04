# DROP TABLE IF EXISTS degrees;
# CREATE TABLE degrees(
#   id INT(11) NOT NULL AUTO_INCREMENT ,
#   degree_name VARCHAR(255) ,
#   PRIMARY KEY (`id`)
# )
#   ENGINE = InnoDB DEFAULT CHARSET = utf8;
#
# -- Table : lectors
# DROP TABLE IF EXISTS lectors;
# CREATE TABLE lectors(
#   id BIGINT(20) NOT NULL AUTO_INCREMENT ,
#   first_name VARCHAR(255) ,
#   last_name VARCHAR(255) ,
#   salary INT ,
#   lector_degree_id INT(11) NOT NULL ,
#   PRIMARY KEY (`id`, `lector_degree_id`) ,
#   KEY `fk_lector_degreeid_idx` (`lector_degree_id`),
#     CONSTRAINT `fk_lector_degreeid` FOREIGN KEY (`lector_degree_id`) REFERENCES degrees(`id`) ON DELETE CASCADE ON UPDATE CASCADE
# )
#   ENGINE = InnoDB DEFAULT CHARSET = utf8;
#
# -- Table : departments
# DROP TABLE IF EXISTS departments;
# CREATE TABLE departments(
# id BIGINT(20) NOT NULL  AUTO_INCREMENT ,
# department_name VARCHAR(255) DEFAULT NULL ,
# head_of_department_id BIGINT(20) NOT NULL ,
# PRIMARY KEY (`id`, `head_of_department_id`) ,
# KEY `fk_department_lector_idx` (`head_of_department_id`) ,
# CONSTRAINT `fk_department_lector` FOREIGN KEY (`head_of_department_id`) REFERENCES `lectors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
# )
#   ENGINE = InnoDB;
#
#
# -- Table : lectors_departments
# DROP TABLE IF EXISTS lectors_departments;
# CREATE TABLE lectors_departments(
#   lector_id BIGINT(20) NOT NULL ,
#   department_id BIGINT(20) NOT NULL ,
#   PRIMARY KEY (`lector_id`, `department_id`) ,
#   CONSTRAINT `fk_lectordepartment_lector` FOREIGN KEY (`lector_id`) REFERENCES `lectors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE ,
#   CONSTRAINT `fk_lectordepartment_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
# )
#   ENGINE = InnoDB DEFAULT CHARSET = utf8;
#
