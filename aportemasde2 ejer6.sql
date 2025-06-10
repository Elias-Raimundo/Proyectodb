SELECT 
    Dni
FROM 
    donaciones.aporte
GROUP BY 
    Dni
HAVING 
    COUNT(DISTINCT nombre_programa) > 2;