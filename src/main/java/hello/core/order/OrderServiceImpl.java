package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy;

    /**
     * 클래스 의존 관계를 분석해 보면, 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
     * 추상(인터페이스) 의존 : DiscountPolicy | 구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy
     * FixDiscountPolicy -> RateDiscountPolicy 구현 클래스 변경 => DIP와 OCP 위반
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
       Member member = memberRepository.findById(memberId);
       int discountPrice = discountPolicy.discount(member, itemPrice);

       return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
