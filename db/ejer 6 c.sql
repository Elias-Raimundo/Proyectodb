SELECT 
    d.Dni,
    p.nombre,
    p.apellido,
    a.nombre_programa,
    a.monto,
    a.nro_pago,
    m.nom_titular
FROM 
    donaciones.donante d
JOIN 
    donaciones.padrino p ON d.Dni = p.Dni
JOIN 
    donaciones.aporte a ON d.Dni = a.Dni
JOIN 
    donaciones.medio_de_pago m ON a.nro_pago = m.nro_pago
WHERE 
    a.frecuencia = 'MENSUAL';