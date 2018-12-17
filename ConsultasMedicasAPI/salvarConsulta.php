<?php 
    $data = $_POST['data'];
    $horario = $_POST['horario'];
    $idMedico = $_POST['idMedico'];
    $nomePaciente = $_POST['nomePaciente'];
    $rgPaciente = $_POST['rgPaciente'];
    $idEspecialidade = $_POST['idEspecialidade'];    

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $sql = " INSERT INTO consulta SET data = '".$data."', horario ='".$horario."', idMedico = ".$idMedico.", nomePaciente ='".$nomePaciente."', rgPaciente ='".$rgPaciente."', idEspecialidade=".$idEspecialidade;

    if(mysqli_query($conexao, $sql)){
        echo json_encode(array("sucesso" => true));
    } else {
        echo json_encode(array("sucesso" => false));
    }
?>