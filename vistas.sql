-- Vista: vw_entrevistado
-- Detalle del entrevistado para descarga masiva y despliegue

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
    ifnull(`en`.`nombre_entrevistador`, '') AS `nombre_entrevistador`,
    `e`.`username` AS `username`
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


-- Vista: vw_grafico_cuatro
-- Cantidad de entrevistas según el tipo de estado

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


-- Vista: vw_grafico_dos
-- Cantidad de entrevistas registradas por día

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

-- Vista: vw_grafico_tres
-- Cantidad de entrevistas por reclutador

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `vw_grafico_tres` AS
select
    coalesce(`e`.`username`, 'Sin asignar') AS `nombre_reclutador`,
    count(1) AS `cantidad_entrevistas`
from
    (`entrevistado` `e`
left join `users` `u` on
    ((`e`.`username` = `u`.`username`)))
group by
    `e`.`username`
order by
    `e`.`username`;


-- Vista: vw_grafico_uno
-- Cantidad de entrevistas por cliente

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