SELECT 
    nombre_programa,
    SUM(monto) AS total_mensual
FROM 
    donaciones.aporte
WHERE 
    frecuencia = 'MENSUAL'
GROUP BY 
    nombre_programa;