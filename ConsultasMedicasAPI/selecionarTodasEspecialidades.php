<?php 
    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $sql = "SELECT * FROM especialidade";

    $select = mysqli_query($conexao, $sql);
    
    $lista = array();

    while($especialidade = mysqli_fetch_assoc($select)){
        $lista[] = $especialidade;
        
    }

    echo json_encode($lista);

?>