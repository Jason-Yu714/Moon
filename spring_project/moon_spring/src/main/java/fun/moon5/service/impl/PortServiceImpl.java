package fun.moon5.service.impl;


import fun.moon5.mapper.PortMapper;
import fun.moon5.pojo.Port;
import fun.moon5.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PortServiceImpl implements PortService {

    @Autowired
    private PortMapper portMapper;

    @Override
    public List<Port> findAll(Integer port) {return portMapper.findAll(port);
    }
}
