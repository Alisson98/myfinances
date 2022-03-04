package github.com.Alisson98.myfinances.adapter.web.exception;

import github.com.Alisson98.myfinances.adapter.web.dto.StandardErrorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ValidationError extends StandardErrorDto {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String error) {
        super(status, error);
    }
    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName,message));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ValidationError that = (ValidationError) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errors);
    }
}