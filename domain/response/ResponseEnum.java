package api.carreras.shared.domain.response;

enum ResponseEnum {
    
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private String text;
    
    ResponseEnum(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
