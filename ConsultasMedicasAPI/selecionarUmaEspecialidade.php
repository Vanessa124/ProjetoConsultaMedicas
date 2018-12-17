<?php 
    $idEspecialidade = $_GET['idEspecialidade'];

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $sql = "SELECT * FROM especialidade WHERE idEspecialidade=".$idEspecialidade;

    $select = mysqli_query($conexao, $sql);

    $especialidade = mysqli_fetch_assoc($select);

    echo json_encode($especialidade);

?>