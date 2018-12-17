<?php 

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $sql = "SELECT c.*, m.nome, e.nome AS especialidade FROM consulta AS c
INNER JOIN medico AS m ON m.idMedico = c.idMedico
INNER JOIN especialidade AS e ON e.idEspecialidade = c.idEspecialidade";


    $select = mysqli_query($conexao, $sql);
    
    $lista = array();

    while($consulta = mysqli_fetch_assoc($select)){
        $lista[] = $consulta;
        
    }

    echo json_encode($lista);
?>