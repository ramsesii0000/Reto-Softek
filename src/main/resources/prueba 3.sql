
CREATE OR REPLACE PACKAGE PKG_EVAL_ASIGNACION AS
    PROCEDURE PRC_ASIGNAR_TIPO_GESTION;
END PKG_EVAL_ASIGNACION;
/

CREATE OR REPLACE PACKAGE BODY PKG_EVAL_ASIGNACION AS

    PROCEDURE PRC_ASIGNAR_TIPO_GESTION IS

        CURSOR cur_eval_req_detalle IS
            SELECT N_ID_REQ_DETALLE, N_MTO
            FROM EVAL_REQ_DETALLE
            FOR UPDATE;

        v_n_id_req_detalle EVAL_REQ_DETALLE.N_ID_REQ_DETALLE%TYPE;
        v_n_mto EVAL_REQ_DETALLE.N_MTO%TYPE;
        v_n_id_tipo_gestion EVAL_REQ_DETALLE.N_ID_TIPO_GESTION%TYPE;
    BEGIN
        OPEN cur_eval_req_detalle;

        LOOP
            FETCH cur_eval_req_detalle INTO v_n_id_req_detalle, v_n_mto;
            EXIT WHEN cur_eval_req_detalle%NOTFOUND;

            IF v_n_mto <= 10 THEN
                v_n_id_tipo_gestion := 6;
            ELSIF v_n_mto > 10 AND v_n_mto <= 35 THEN
                v_n_id_tipo_gestion := 5;
            ELSIF v_n_mto > 35 AND v_n_mto <= 100 THEN
                v_n_id_tipo_gestion := 4;
            ELSE
                v_n_id_tipo_gestion := 3;
            END IF;

            UPDATE EVAL_REQ_DETALLE
            SET N_ID_TIPO_GESTION = v_n_id_tipo_gestion,
                D_FEC_GESTION = SYSDATE
            WHERE N_ID_REQ_DETALLE = v_n_id_req_detalle;
        END LOOP;

        CLOSE cur_eval_req_detalle;

        COMMIT;

    EXCEPTION
        WHEN OTHERS THEN
            IF cur_eval_req_detalle%ISOPEN THEN
                CLOSE cur_eval_req_detalle;
            END IF;
            RAISE;
    END PRC_ASIGNAR_TIPO_GESTION;
END PKG_EVAL_ASIGNACION;
/

BEGIN
    PKG_EVAL_ASIGNACION.PRC_ASIGNAR_TIPO_GESTION;
END;
/

ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY HH24:MI:SS';

SELECT N_ID_REQ_DETALLE,
       N_ID_RQ,
       C_COD_AUTORIZACION,
       TO_CHAR(D_FEC_TRX, 'DD/MM/YYYY') AS D_FEC_TRX,
       N_MTO,
       C_USU_REGISTRO,
       TO_CHAR(D_FEC_REGISTRO, 'DD/MM/YYYY HH24:MI:SS') AS D_FEC_REGISTRO,
       N_ID_TIPO_GESTION,
       TO_CHAR(D_FEC_GESTION, 'DD/MM/YYYY HH24:MI:SS') AS D_FEC_GESTION
FROM EVAL_REQ_DETALLE;