module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    exports com.epam.jmp.cloud.service.impl;
    provides com.epam.jmp.service.api with com.epam.jmp.cloud.service.impl.ServiceImpl;
}