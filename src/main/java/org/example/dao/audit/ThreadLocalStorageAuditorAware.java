package org.example.dao.audit;

// TODO может понадобиться если нужно получить значение из контекста
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
//
//@Component
//public class ThreadLocalStorageAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of((String) Objects.requireNonNull(RequestContextHolder
//                .currentRequestAttributes()
//                .getAttribute("Username", SCOPE_REQUEST)));
//    }
//
//}
