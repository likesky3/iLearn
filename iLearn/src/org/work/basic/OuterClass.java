package org.work.basic;

public class OuterClass {

    private int ab = 1;
    private static int sab = 2;

    /**
     * ��ͨ�ڲ���
     */
    public class NormalInnerClass {

        // private static int age = 22;
        private int age = 22; // ��������Ϊstatic

        public NormalInnerClass() {
            // ���Է����ⲿ�ྲ̬��Ǿ�̬��Ա
            System.out.println(ab);
            System.out.println(sab);
        }
    }

    /**
     * ��̬�ڲ���
     */
    public static class StaticInnerClass {
        // ���徲̬��Ǿ�̬��Ա���ǿ��Ե�
        private static int age = 22;
        public int age2 = 22;

        private void echo() {
            // System.out.println(ab);
            System.out.println(sab);// ֻ�ܷ����ⲿ��ľ�̬��Ա
        }
    }
}
