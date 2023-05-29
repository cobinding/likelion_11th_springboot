package likelion.springbootbobae.controller;

import likelion.springbootbobae.domain.Address;
import likelion.springbootbobae.domain.Member;
import likelion.springbootbobae.dto.MemberDto;
import likelion.springbootbobae.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * @Controller: MVC 패턴의 Controller임을 명시하고, spring bean에 해당 컨트롤러를 등록한다.
 * @RestController: REST API를 제공하는 컨트롤러를 위한 어노테이션. html view 형식이 아닌 JSON과 같은 데이터를 client에게 제공한다.
 *                  @RequestBody, @ResponseBody와 함께 사용.
 */
@Controller
/*
 * @RequestMapping(url 경로): 요청 url을 컨트롤러의 메소드, 클래스와 매핑할 때 사용된다.
 * 사용자가 해당 url로 접속하면 이 annotation이 붙어있는 method가 실행된다.
 * 아래 코드는 class level에서 mapping 하였으므로 해당 컨트롤러의 모든 요청 메소드에 적용된다.
 *
 * */
@RequestMapping("members")
public class MemberController {

    /* final을 통해 값 변경 제어
     * private을 통해 외부 접근 제어
     * */
    private final MemberService memberService;

    /*
     * @Autowired: 객체 간의 관계(의존 관계 설정)
     * 의존 관계 주입 방법 = [생성자 주입, Setter 주입, 필드 주입, 일반 메서드 주입] 중 아래는 "생성자 주입"방법이다.
     * 생성자 주입 방법은 불변이 보장되므로 안정성이 높다.(Setter나 메서드 주입 등을 사용하면 외부에서 실수로 변경할 수 있다.)
     * 또한 데이터 누락 시 compile error를 발새하므로, 의존관계 주입 누락에 대해 빠르게 감지할 수 있다.
     */

    /*
     * MemberController의 생성자에 MemberService 타입의 매개변수가 선언되어 있고, @Autowired 어노테이션이 적용되어있다.
     * 이는 Spring이 MemberService의 인스턴스를 생성하고, 해당 인스턴스를 MemberController의 생성자를 통해 주입한다는 뜻이다.
     * 쉽게 말해서 MemberService 객체를 생성하고 연결하는 것이다. MemberController는 MemberService의 메서드를 호출하거나
     * 멤버 변수에 접근할 수 있게 된다.
     * */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* http 메서드 중 Get 요청 url을 처리하기 위한 mapping Annotation.

     * ⭐️ spring Model 인터페이스를 통해서 Contoller -> View 데이터 전달
     * addAttribute() 메서드로 데이터를 추가할 수 있고, 이를 통해 뷰에서 해당 데이터를 사용할 수 있게 된다.

     ** addAttribute: 데이터의 key와 value를 인자로 받는다.

     */
    @GetMapping("new")
    public String createForm(Model model) {
        // memberForm이라는 이름으로 새로운 MemberDto 객체를 생성하여 Model에 추가한다.
        model.addAttribute("memberForm", new MemberDto.Create());
        // 뷰 페이지 값을 return으로 작성한다. 이렇게 반환된 뷰 페이지는 클라이언트로 전달되어 렌더링된다.
        // 클라이언트는 아래 경로에서 MemberDto 객체의 데이터를 사용할 수 있다.
        return "members/createMemberForm";
    }

    /*
     * 회원 정보를 받아와서 회원 객체를 생성하고, 생성된 회원을 저장한 후에 리다이렉트한다.
     * MemberDto 객체를 매게변수로 받아서 memberDto에서 name, city, state, street 등의 정보를 getter로 가져온다.
     * */
    @PostMapping("new")
    public String create(MemberDto.Create memberDto) {
        String name = memberDto.getName();
        String city = memberDto.getCity();
        String state = memberDto.getState();
        String street = memberDto.getStreet();
        String zipcode = memberDto.getZipcode();
        Address address = new Address(city, state, street, zipcode);

        // Member 객체 생성
        Member member = Member.createMember(name, address);
        // 생성된 회원 객체 db에 저장
        memberService.save(member);
        // 회원 저장을 완료한 뒤 "/"경로로 리다이렉트한다. -> 클라이언트를 메인 페이지로 이동..
        return "redirect:/";
    }

    /*
     * Get요청이 들어오면 memberService에서 findAll 메소드를 통해 멤버의 리스트를 반환한다. */
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }
}


