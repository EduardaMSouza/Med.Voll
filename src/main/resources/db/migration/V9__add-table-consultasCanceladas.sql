CREATE TABLE consultas_canceladas (
                           id INTEGER PRIMARY KEY,
                           idMedico INTEGER,
                           idPaciente INTEGER,
                           data VARCHAR(30),
                           motivo_cancelamento VARCHAR(100)
);
