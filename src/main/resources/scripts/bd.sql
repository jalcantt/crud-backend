CREATE TABLE productos (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL,
    descripcion VARCHAR2(255),
    precio NUMBER(10, 2) NOT NULL,
    fecha_creacion DATE DEFAULT SYSDATE,
    estado NUMBER(1) DEFAULT 1 -- 1 para activo, 0 para inactivo
);

CREATE TABLE clientes (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL,
    email VARCHAR2(255) UNIQUE NOT NULL,
    telefono VARCHAR2(50)
);

CREATE TABLE ordenes (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    cantidad NUMBER NOT NULL,
    total NUMBER(10, 2) NOT NULL,
    fecha_orden DATE DEFAULT SYSDATE,
    id_producto NUMBER REFERENCES productos(id),
    id_cliente NUMBER REFERENCES clientes(id)
);
