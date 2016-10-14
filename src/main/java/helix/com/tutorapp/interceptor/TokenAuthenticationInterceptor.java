//package helix.com.tutorapp.interceptor;
//
//
//import helix.com.tutorapp.controller.stereotype.NoAuthentication;
//import helix.com.tutorapp.controller.stereotype.RequiredRoles;
//import helix.com.tutorapp.exception.InterceptorException;
//import helix.com.tutorapp.model.Role;
//import helix.com.tutorapp.model.User;
//import helix.com.tutorapp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class TokenAuthenticationInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public TokenAuthenticationInterceptor() {
//        // sampleTokenRoleMapping.put(userService.findUserToken(token),"Register");
//        //sampleTokenRoleMapping.put("0001", "Registered");
//        //sampleTokenRoleMapping.put("0002", "Administrator");
//        // Phải lấy các thông tin này từ database, đây là dữ liệu mẫu fixed
//        //  sampleTokenRoleMapping.put("")
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("auth-token");
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            if (handlerMethod.getMethodAnnotation(NoAuthentication.class) != null) {
//                // No required authentication
//                return true; // by pass
//            } else if (token == null) {
//                throw new InterceptorException("Required token", HttpStatus.UNAUTHORIZED); // By default required token
//            }
//
//            // 1. Find user by token
//            // If user doesn't exist, mean token is invalid
//            // Else check the role for user is match with method in controller
//            User user = userRepository.findByToken(token);
//            Role role = user.getRole();
//            RequiredRoles requiredRoles = handlerMethod.getMethodAnnotation(RequiredRoles.class);
//            if (requiredRoles != null) {
//                for (Role requiredRole : requiredRoles.value()) {
//                    if (requiredRole.equals(role)) {
//                        return true;
//                    }
//                }
//                throw new InterceptorException("No matching role found", HttpStatus.UNAUTHORIZED);
//            }
//        }
//        return true;
//    }
//}
