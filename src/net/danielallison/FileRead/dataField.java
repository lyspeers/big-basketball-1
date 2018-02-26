package net.danielallison.FileRead;

class dataField< T > {
    private String fieldName;
    private T data;

    dataField(T data) {
        this.data = data;
    }

    public dataField(T data, String fieldName) {
        this.data = data;
        this.fieldName = fieldName;
    }

    public T getData() {
        return data;
    }

    public void changeData(T data) {
        this.data = data;
    }
}