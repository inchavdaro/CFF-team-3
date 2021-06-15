package ccf.project.domain.converters;

import ccf.project.domain.enums.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole category) {
        if (category == null) {
            return null;
        }
        return category.code;
    }

    @Override
    public UserRole convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(UserRole.values())
                .filter(c -> c.code == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
