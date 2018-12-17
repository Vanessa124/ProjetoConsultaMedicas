<?php 
    $idMedico = $_GET['idMedico'];
    $idDia = $_GET['idDia'];
    $dataEscolhida = $_GET['dataEscolhida'];

    $sql = "SELECT m.idMedico, m.nome, ha.idHorarioAtendimento,ha.horario_inicio, ha.horario_fim, s.idDiaSemana, s.diaSemana FROM medico AS M
INNER JOIN horario_atendimento AS ha ON ha.idMedico = m.idMedico
INNER JOIN dia_semana AS s ON ha.idDiaSemana = s.idDiaSemana
WHERE s.idDiaSemana = ".$idDia." AND m.idMedico =".$idMedico;

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $select = mysqli_query($conexao, $sql);
    
    $lista = array();

    while($horarioAtendimento = mysqli_fetch_assoc($select)){
        
        //Pega as consultas que o médico tem agendado no dia escolhido (variável dataEscolhida)
        $sqlConsulta = "SELECT * FROM consulta WHERE idMedico = ".$idMedico." AND data='".$dataEscolhida."'";
        
        $selectConsulta = mysqli_query($conexao, $sqlConsulta);
    
        $listaConsulta = array();
        
        while($consulta = mysqli_fetch_assoc($selectConsulta)){
            
            $listaConsulta[] = $consulta;
        }
        
        $horarioAtendimento["consultas"] = $listaConsulta;
        $lista[] = $horarioAtendimento;
    }

    echo json_encode($lista);



    
?>