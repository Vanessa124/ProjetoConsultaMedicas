<?php 

    $idEspecialidade = $_GET['idEspecialidade'];

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $sql = "SELECT e.nome, m.idMedico, m.nome FROM medico AS m
INNER JOIN especialidade_medico AS em ON m.idMedico = em.idMedico
INNER JOIN especialidade AS e ON e.idEspecialidade = em.idEspecialidade WHERE e.idEspecialidade = ".$idEspecialidade;

    $select = mysqli_query($conexao, $sql);
    
    $lista = array();

    while($especialidade = mysqli_fetch_assoc($select)){
        $lista[] = $especialidade;
        
    }

    echo json_encode($lista);

?>