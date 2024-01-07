-- Tabla banco

CREATE TABLE `banco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_banco` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla canal

CREATE TABLE `canal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_canal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla cargo

CREATE TABLE `cargo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_cargo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla contactado

CREATE TABLE `contactado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado_contactado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla entrevistado_eliminado 

CREATE TABLE `entrevistado_eliminado` (
  `id_entrevistado` int NOT NULL,
  `ap_materno` varchar(255) DEFAULT NULL,
  `ap_paterno` varchar(255) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `banco_id` int DEFAULT NULL,
  `calzado` varchar(255) DEFAULT NULL,
  `canal_id` int DEFAULT NULL,
  `cargo_id` int DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  `comuna_contacto` varchar(255) DEFAULT NULL,
  `comuna_contacto_id` int DEFAULT NULL,
  `comuna_id` int DEFAULT NULL,
  `contactado` varchar(255) DEFAULT NULL,
  `contactado_id` int DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `detalle_nacionalidad` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `direccion_contacto` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `entrevistador_id` int DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estado_id` int DEFAULT NULL,
  `estado_validado` varchar(255) DEFAULT NULL,
  `fecha_contratacion` varchar(255) DEFAULT NULL,
  `fecha_contratacion_rv` varchar(255) DEFAULT NULL,
  `fecha_estado` varchar(255) DEFAULT NULL,
  `fecha_estado_rv` varchar(255) DEFAULT NULL,
  `fecha_ingreso` varchar(255) DEFAULT NULL,
  `fecha_ingreso_rv` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` varchar(255) DEFAULT NULL,
  `fecha_nacimiento_rv` varchar(255) DEFAULT NULL,
  `instalacion_id` int DEFAULT NULL,
  `nacionalidad_id` int DEFAULT NULL,
  `nombre_canal` varchar(255) DEFAULT NULL,
  `nombre_cargo` varchar(255) DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `nombre_comuna` varchar(255) DEFAULT NULL,
  `nombre_contacto` varchar(255) DEFAULT NULL,
  `nombre_entrevistador` varchar(255) DEFAULT NULL,
  `nombre_instalacion` varchar(255) DEFAULT NULL,
  `nombre_reclutador` varchar(255) DEFAULT NULL,
  `nombre_region` varchar(255) DEFAULT NULL,
  `nombre_region_contacto` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `numero_direccion` varchar(255) DEFAULT NULL,
  `observacion_complementaria` varchar(255) DEFAULT NULL,
  `observacion_contacto` varchar(255) DEFAULT NULL,
  `observacion_registro` varchar(255) DEFAULT NULL,
  `pantalon` varchar(255) DEFAULT NULL,
  `pantalon_id` int DEFAULT NULL,
  `parentesco` varchar(255) DEFAULT NULL,
  `parentescocontacto_id` int DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `periodo_id` int DEFAULT NULL,
  `polera` varchar(255) DEFAULT NULL,
  `polera_id` int DEFAULT NULL,
  `poleron` varchar(255) DEFAULT NULL,
  `poleron_id` int DEFAULT NULL,
  `presentacion` varchar(255) DEFAULT NULL,
  `presentacion_id` int DEFAULT NULL,
  `prevision` varchar(255) DEFAULT NULL,
  `prevision_id` int DEFAULT NULL,
  `reclutador_id` int DEFAULT NULL,
  `region_contacto_id` int DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `run` varchar(255) DEFAULT NULL,
  `salud` varchar(255) DEFAULT NULL,
  `salud_id` int DEFAULT NULL,
  `seguro_covid` varchar(255) DEFAULT NULL,
  `segurocovid_id` int DEFAULT NULL,
  `servicio` varchar(255) DEFAULT NULL,
  `servicio_id` int DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `telefono_contacto` varchar(255) DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `tipocuenta_id` int DEFAULT NULL,
  `validado_id` int DEFAULT NULL,
  PRIMARY KEY (`id_entrevistado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla estado

CREATE TABLE `estado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla nacionalidad

CREATE TABLE `nacionalidad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla parentesco

CREATE TABLE `parentesco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_parentesco` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla periodo

CREATE TABLE `periodo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_periodo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla presentacion

CREATE TABLE `presentacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado_presentacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla prevision

CREATE TABLE `prevision` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_prevision` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla region

CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo_region` varchar(255) DEFAULT NULL,
  `nombre_region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla salud

CREATE TABLE `salud` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_salud` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla segurocovid

CREATE TABLE `segurocovid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado_seguro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla servicio

CREATE TABLE `servicio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_servicio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla talla

CREATE TABLE `talla` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_talla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla tipocuenta

CREATE TABLE `tipocuenta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detalle_tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla users

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla validado

CREATE TABLE `validado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado_validado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla comuna

CREATE TABLE `comuna` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_comuna` varchar(255) DEFAULT NULL,
  `region_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comuna_region` (`region_id`),
  CONSTRAINT `FK_comuna_region` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla entrevistador

CREATE TABLE `entrevistador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_entrevistador` varchar(255) DEFAULT NULL,
  `comuna_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entrevistador_comuna` (`comuna_id`),
  CONSTRAINT `FK_entrevistador_comuna` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla instalacion

CREATE TABLE `instalacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_instalacion` varchar(255) DEFAULT NULL,
  `rut_instalacion` varchar(255) DEFAULT NULL,
  `comuna_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_instalacion_comuna` (`comuna_id`),
  CONSTRAINT `FK_instalacion_comuna` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla reclutador

CREATE TABLE `reclutador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_reclutador` varchar(255) DEFAULT NULL,
  `comuna_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reclutador_comuna` (`comuna_id`),
  CONSTRAINT `FK_reclutador_comuna` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla user_roles 

CREATE TABLE `user_roles` (
  `user_role_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `UK_role_username` (`role`,`username`),
  KEY `FK_user_roles_users` (`username`),
  CONSTRAINT `FK_user_roles_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla cliente 

CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `rut_cliente` varchar(255) DEFAULT NULL,
  `comuna_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cliente_comuna` (`comuna_id`),
  CONSTRAINT `FK_cliente_comuna` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Tabla entrevistado 

CREATE TABLE `entrevistado` (
  `id_entrevistado` int NOT NULL AUTO_INCREMENT,
  `ap_materno` varchar(255) DEFAULT NULL,
  `ap_paterno` varchar(255) DEFAULT NULL,
  `banco_cuenta` int DEFAULT NULL,
  `calzado` varchar(255) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `contactado` int DEFAULT NULL,
  `correo_electronico` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `direccion_contacto` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `estado` int DEFAULT NULL,
  `fecha_contratacion` datetime(6) DEFAULT NULL,
  `fecha_estado` datetime(6) DEFAULT NULL,
  `fecha_ingreso` datetime(6) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `nacionalidad` int DEFAULT NULL,
  `nombre_contacto` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `numero_direccion` varchar(255) DEFAULT NULL,
  `observacion_complementaria` varchar(255) DEFAULT NULL,
  `observacion_contacto` varchar(255) DEFAULT NULL,
  `observacion_registro` varchar(255) DEFAULT NULL,
  `pantalon` int DEFAULT NULL,
  `parentezco_contacto` int DEFAULT NULL,
  `periodo` int DEFAULT NULL,
  `polera` int DEFAULT NULL,
  `poleron` int DEFAULT NULL,
  `presentacion` int DEFAULT NULL,
  `prevision` int DEFAULT NULL,
  `run` varchar(255) DEFAULT NULL,
  `salud` int DEFAULT NULL,
  `seguro_covid` int DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `telefono_contacto` varchar(255) DEFAULT NULL,
  `tipo_cuenta` int DEFAULT NULL,
  `tipo_servicio` int DEFAULT NULL,
  `validado` int DEFAULT NULL,
  `canal_id` int NOT NULL,
  `cargo_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  `comuna_id` int NOT NULL,
  `comuna_contacto_id` int DEFAULT NULL,
  `entrevistador_id` int DEFAULT NULL,
  `instalacion_id` int DEFAULT NULL,
  `reclutador_id` int NOT NULL,
  PRIMARY KEY (`id_entrevistado`),
  KEY `FK_entrevistado_canal` (`canal_id`),
  KEY `FK_entrevistado_cargo` (`cargo_id`),
  KEY `FK_entrevistado_cliente` (`cliente_id`),
  KEY `FK_entrevistado_comuna` (`comuna_id`),
  KEY `FK_entrevistado_comuna_contacto` (`comuna_contacto_id`),
  KEY `FK_entrevistado_entrevistador` (`entrevistador_id`),
  KEY `FK_entrevistado_instalacion` (`instalacion_id`),
  KEY `FK_entrevistado_reclutador` (`reclutador_id`),
  CONSTRAINT `FK_entrevistado_canal` FOREIGN KEY (`canal_id`) REFERENCES `canal` (`id`),
  CONSTRAINT `FK_entrevistado_reclutador` FOREIGN KEY (`reclutador_id`) REFERENCES `reclutador` (`id`),
  CONSTRAINT `FK_entrevistado_cargo` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`),
  CONSTRAINT `FK_entrevistado_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK_entrevistado_comuna` FOREIGN KEY (`comuna_id`) REFERENCES `comuna` (`id`),
  CONSTRAINT `FK_entrevistado_entrevistador` FOREIGN KEY (`entrevistador_id`) REFERENCES `entrevistador` (`id`),
  CONSTRAINT `FK_entrevistado_instalacion` FOREIGN KEY (`instalacion_id`) REFERENCES `instalacion` (`id`),
  CONSTRAINT `FK_entrevistado_comuna_contacto` FOREIGN KEY (`comuna_contacto_id`) REFERENCES `comuna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Vista vw_entrevistado

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_entrevistado` AS
select
    `e`.`id_entrevistado` AS `id_entrevistado`,
    date_format(`e`.`fecha_ingreso`, '%d-%m-%Y') AS `fecha_ingreso`,
    date_format(`e`.`fecha_ingreso`, '%Y-%m-%d') AS `fecha_ingreso_rv`,
    `e`.`reclutador_id` AS `reclutador_id`,
    `r`.`nombre_reclutador` AS `nombre_reclutador`,
    `e`.`run` AS `run`,
    `e`.`cliente_id` AS `cliente_id`,
    `c`.`nombre_cliente` AS `nombre_cliente`,
    `e`.`nombres` AS `nombres`,
    `e`.`ap_paterno` AS `ap_paterno`,
    ifnull(`e`.`ap_materno`, '') AS `ap_materno`,
    ifnull(`e`.`correo_electronico`, '') AS `correo_electronico`,
    ifnull(`e`.`telefono`, '') AS `telefono`,
    ifnull(`e`.`observacion_registro`, '') AS `observacion_registro`,
    ifnull(`e`.`empresa`, '') AS `empresa`,
    `e`.`validado` AS `validado_id`,
    ifnull(`v`.`estado_validado`, '') AS `estado_validado`,
    ifnull(date_format(`e`.`fecha_estado`, '%d-%m-%Y'), '') AS `fecha_estado`,
    date_format(`e`.`fecha_estado`, '%Y-%m-%d') AS `fecha_estado_rv`,
    `e`.`estado` AS `estado_id`,
    ifnull(`es`.`detalle_estado`, '') AS `estado`,
    `e`.`instalacion_id` AS `instalacion_id`,
    ifnull(`i`.`nombre_instalacion`, '') AS `nombre_instalacion`,
    `e`.`cargo_id` AS `cargo_id`,
    ifnull(`cr`.`nombre_cargo`, '') AS `nombre_cargo`,
    `cm`.`region_id` AS `region_id`,
    `rg`.`nombre_region` AS `nombre_region`,
    `e`.`comuna_id` AS `comuna_id`,
    `cm`.`nombre_comuna` AS `nombre_comuna`,
    `e`.`periodo` AS `periodo_id`,
    ifnull(`pr`.`detalle_periodo`, '') AS `periodo`,
    `e`.`tipo_servicio` AS `servicio_id`,
    ifnull(`sr`.`detalle_servicio`, '') AS `servicio`,
    ifnull(date_format(`e`.`fecha_contratacion`, '%d-%m-%Y'), '') AS `fecha_contratacion`,
    ifnull(date_format(`e`.`fecha_contratacion`, '%Y-%m-%d'), '') AS `fecha_contratacion_rv`,
    ifnull(`e`.`observacion_complementaria`, '') AS `observacion_complementaria`,
    `e`.`canal_id` AS `canal_id`,
    `cn`.`nombre_canal` AS `nombre_canal`,
    `e`.`contactado` AS `contactado_id`,
    ifnull(`ct`.`estado_contactado`, '') AS `contactado`,
    `e`.`presentacion` AS `presentacion_id`,
    ifnull(`pt`.`estado_presentacion`, '') AS `presentacion`,
    ifnull(`e`.`observacion_contacto`, '') AS `observacion_contacto`,
    `e`.`nacionalidad` AS `nacionalidad_id`,
    ifnull(`n`.`detalle_nacionalidad`, '') AS `detalle_nacionalidad`,
    ifnull(date_format(`e`.`fecha_nacimiento`, '%d-%m-%Y'), '') AS `fecha_nacimiento`,
    ifnull(date_format(`e`.`fecha_nacimiento`, '%Y-%m-%d'), '') AS `fecha_nacimiento_rv`,
    ifnull(timestampdiff(YEAR, `e`.`fecha_nacimiento`, curdate()), 0) AS `edad`,
    ifnull(`e`.`direccion`, '') AS `direccion`,
    ifnull(`e`.`ciudad`, '') AS `ciudad`,
    ifnull(`e`.`numero_direccion`, '') AS `numero_direccion`,
    `e`.`prevision` AS `prevision_id`,
    ifnull(`pv`.`detalle_prevision`, '') AS `prevision`,
    `e`.`salud` AS `salud_id`,
    ifnull(`sd`.`detalle_salud`, '') AS `salud`,
    `e`.`seguro_covid` AS `segurocovid_id`,
    ifnull(`sc`.`estado_seguro`, '') AS `seguro_covid`,
    `e`.`tipo_cuenta` AS `tipocuenta_id`,
    ifnull(`tc`.`detalle_tipo`, '') AS `tipo_cuenta`,
    `e`.`banco_cuenta` AS `banco_id`,
    ifnull(`bc`.`nombre_banco`, '') AS `banco`,
    ifnull(`e`.`numero_cuenta`, '') AS `numero_cuenta`,
    ifnull(`e`.`calzado`, '') AS `calzado`,
    `e`.`polera` AS `polera_id`,
    ifnull(`tpl`.`nombre_talla`, '') AS `polera`,
    `e`.`poleron` AS `poleron_id`,
    ifnull(`tpn`.`nombre_talla`, '') AS `poleron`,
    `e`.`pantalon` AS `pantalon_id`,
    ifnull(`tpt`.`nombre_talla`, '') AS `pantalon`,
    ifnull(`e`.`nombre_contacto`, '') AS `nombre_contacto`,
    ifnull(`e`.`telefono_contacto`, '') AS `telefono_contacto`,
    ifnull(`e`.`direccion_contacto`, '') AS `direccion_contacto`,
    `e`.`parentezco_contacto` AS `parentescocontacto_id`,
    ifnull(`prt`.`nombre_parentesco`, '') AS `parentesco`,
    `e`.`comuna_contacto_id` AS `comuna_contacto_id`,
    ifnull(`cc`.`nombre_comuna`, '') AS `comuna_contacto`,
    `cc`.`region_id` AS `region_contacto_id`,
    ifnull(`rc`.`nombre_region`, '') AS `nombre_region_contacto`,
    `e`.`entrevistador_id` AS `entrevistador_id`,
    ifnull(`en`.`nombre_entrevistador`, '') AS `nombre_entrevistador`
from
    ((((((((((((((((((((((((((`entrevistado` `e`
left join `reclutador` `r` on
    ((`e`.`reclutador_id` = `r`.`id`)))
left join `cliente` `c` on
    ((`e`.`cliente_id` = `c`.`id`)))
left join `validado` `v` on
    ((`e`.`validado` = `v`.`id`)))
left join `estado` `es` on
    ((`e`.`estado` = `es`.`id`)))
left join `instalacion` `i` on
    ((`e`.`instalacion_id` = `i`.`id`)))
left join `cargo` `cr` on
    ((`e`.`cargo_id` = `cr`.`id`)))
left join `comuna` `cm` on
    ((`e`.`comuna_id` = `cm`.`id`)))
left join `region` `rg` on
    ((`cm`.`region_id` = `rg`.`id`)))
left join `periodo` `pr` on
    ((`e`.`periodo` = `pr`.`id`)))
left join `servicio` `sr` on
    ((`e`.`tipo_servicio` = `sr`.`id`)))
left join `canal` `cn` on
    ((`e`.`canal_id` = `cn`.`id`)))
left join `contactado` `ct` on
    ((`e`.`contactado` = `ct`.`id`)))
left join `presentacion` `pt` on
    ((`e`.`presentacion` = `pt`.`id`)))
left join `nacionalidad` `n` on
    ((`e`.`nacionalidad` = `n`.`id`)))
left join `prevision` `pv` on
    ((`e`.`prevision` = `pv`.`id`)))
left join `salud` `sd` on
    ((`e`.`salud` = `sd`.`id`)))
left join `segurocovid` `sc` on
    ((`e`.`seguro_covid` = `sc`.`id`)))
left join `tipocuenta` `tc` on
    ((`e`.`tipo_cuenta` = `tc`.`id`)))
left join `banco` `bc` on
    ((`e`.`banco_cuenta` = `bc`.`id`)))
left join `talla` `tpl` on
    ((`e`.`polera` = `tpl`.`id`)))
left join `talla` `tpn` on
    ((`e`.`poleron` = `tpn`.`id`)))
left join `talla` `tpt` on
    ((`e`.`pantalon` = `tpt`.`id`)))
left join `parentesco` `prt` on
    ((`e`.`parentezco_contacto` = `prt`.`id`)))
left join `comuna` `cc` on
    ((`e`.`comuna_contacto_id` = `cc`.`id`)))
left join `region` `rc` on
    ((`cc`.`region_id` = `rc`.`id`)))
left join `entrevistador` `en` on
    ((`e`.`entrevistador_id` = `en`.`id`)));

-- Vista vw_grafico_uno

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_grafico_uno` AS
select
    `e`.`cliente_id` AS `cliente_id`,
    `c`.`nombre_cliente` AS `nombre_cliente`,
    count(0) AS `cantidad_entrevistas`
from
    (`entrevistado` `e`
left join `cliente` `c` on
    ((`e`.`cliente_id` = `c`.`id`)))
group by
    `c`.`nombre_cliente`,
    `e`.`cliente_id`
order by
    `c`.`nombre_cliente`;

-- Vista vw_grafico_dos

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_grafico_dos` AS
select
    date_format(`A`.`fecha_ingreso`, '%d-%m') AS `str_fecha_ingreso`,
    `A`.`cantidad_entrevistas` AS `cantidad_entrevistas`
from
    (
    select
        `e`.`fecha_ingreso` AS `fecha_ingreso`,
        count(1) AS `cantidad_entrevistas`
    from
        `entrevistado` `e`
    group by
        `e`.`fecha_ingreso`
    order by
        `e`.`fecha_ingreso` desc
    limit 10) `A`
order by
    `A`.`fecha_ingreso`;

-- Vista vw_grafico_tres

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_grafico_tres` AS
select
    `r`.`nombre_reclutador` AS `nombre_reclutador`,
    count(1) AS `cantidad_entrevistas`
from
    (`entrevistado` `e`
left join `reclutador` `r` on
    ((`e`.`reclutador_id` = `r`.`id`)))
group by
    `r`.`nombre_reclutador`
order by
    `r`.`nombre_reclutador`;

-- Vista vw_grafico_cuatro

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_grafico_cuatro` AS
select
    ifnull(`t`.`detalle_estado`, '(SIN DATOS)') AS `detalle_estado`,
    count(1) AS `cantidad_entrevistas`
from
    (`entrevistado` `e`
left join `estado` `t` on
    ((`e`.`estado` = `t`.`id`)))
group by
    `t`.`detalle_estado`
order by
    `t`.`detalle_estado`;