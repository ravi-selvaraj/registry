-- SEQUENCE: public.patient_records_patient_id_seq

-- DROP SEQUENCE public.patient_records_patient_id_seq;

CREATE SEQUENCE public.patient_records_patient_id_seq;

ALTER SEQUENCE public.patient_records_patient_id_seq
    OWNER TO idhayangal;
    
-- Table: public.patient_records

-- DROP TABLE public.patient_records;

CREATE TABLE public.patient_records
(
    patient_id integer NOT NULL DEFAULT nextval('patient_records_patient_id_seq'::regclass),
    patient_record jsonb NOT NULL,
    patient_name character varying(200) COLLATE pg_catalog."default",
    patient_dob character varying(20) COLLATE pg_catalog."default",
    patient_city character varying(200) COLLATE pg_catalog."default",
    patient_year_of_diagnosis character varying(10) COLLATE pg_catalog."default",
    patient_phone character varying(50) COLLATE pg_catalog."default",
    patient_tags character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT patient_records_pkey PRIMARY KEY (patient_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.patient_records
    OWNER to idhayangal;

-- Index: patient_record_expr_idx

-- DROP INDEX public.patient_record_expr_idx;

CREATE INDEX patient_record_expr_idx
    ON public.patient_records USING gin
    ((patient_record -> 'tags'::text))
    TABLESPACE pg_default;