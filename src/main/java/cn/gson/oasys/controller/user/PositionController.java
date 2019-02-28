package cn.gson.oasys.controller.user;

import cn.gson.oasys.model.entity.user.Position;

import cn.gson.oasys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class PositionController  {
    @Autowired
    private PositionService positionService;

    @RequestMapping("/positionList")
    public   String   positionList(Model model){
        List<Position> list = positionService.findAll();
        model.addAttribute("positions",list);
        return  "user/positionmanage";
    }

    @RequestMapping("/positiontoAdd")
    public String positiontoAdd() {
        return "user/positionadd";
    }

    @RequestMapping("/positionadd")
    public String positionadd(Position position) {
        positionService.save(position);
        return "redirect:/positionList";
    }

    @RequestMapping("/positiontoEdit")
    public String toEdit(Model model, Long id) {
       Position position = positionService.findpositionById(id);
        model.addAttribute("position", position);
        return "user/positionedit";
    }

    @RequestMapping("/positionedit")
    public String edit(Position position) {
        positionService.update(position);
        return "redirect:/positionList";
    }

}
