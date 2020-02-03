--Crear un dispositivo de Backup
USE ApuestasDeportivas
GO
	EXEC sp_addumpdevice 'disk','mydiskdump2','Escritorio';
GO

--Eliminar un dispositivo de Backup
--EXEC sp_dropdevice 'mydiskdump'

--Poner la base de datos en modo de solo lectura
ALTER DATABASE[ApuestasDeportivas] SET READ_ONLY
BACKUP DATABASE ApuestasDeportivas TO mydiskdump2

--Poner la base de datos en modo normal
ALTER DATABASE[ApuestasDeportivas] SET READ_WRITE

--Crear trabajo del agente
Use msdb
GO
EXEC sp_add_job
	@job_name = 'BackupDiurno'
GO

--Añadir el primer paso a la tarea
GO
EXEC sp_add_jobstep
	@job_name = 'BackupDiurno',
	@step_name = 'Set database to read only',
	@subsystem = 'TSQL',
	@command = 'ALTER DATABASE ApuestasDeportivas SET READ_ONLY',
	@retry_attempts = 5, --nº de intentos
	@retry_interval = 5 --Tiempo en minutos que estara intentandolo
GO

--Programar la tarea
GO
EXEC sp_add_schedule
@schedule_name ='EjercutarBackupDiurno',
@freq_type = 4, --Cada 4 días
@freq_interval = 1, --Lunes
@active_start_time = 111500 --11:15
GO

--Adjuntar programa al trabajo
GO
EXEC sp_attach_schedule 
	@job_name = 'BackupDiurno',
	@schedule_name = 'EjercutarBackupDiurno'
GO

