package com.kijlee.android.demo.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity
 * @ClassName:      ClickEntity
 * @Author:     kij
 * @Description:  用于显示不同列表的布局区分
 * @Date:    2022/1/22 8:36 下午
 * @Version:    1.0
 */
class ClickEntity constructor(type:Int): MultiItemEntity {
    constructor():this(0)
    var type = 0

    init {
        this.type = type
    }

    override val itemType: Int
        get() = type

    companion object {

        val TYPE_ITEM_0 = 0
        val TYPE_ITEM_1 = 1
        val TYPE_ITEM_2 = 2
        val TYPE_ITEM_3 = 3
        val TYPE_ITEM_4 = 4
        val TYPE_ITEM_5 = 5
        val TYPE_ITEM_6 = 6
        val TYPE_ITEM_7 = 7
        val TYPE_ITEM_8 = 8
        val TYPE_ITEM_9 = 9
        val TYPE_ITEM_10 = 10
        val TYPE_ITEM_11 = 11
        val TYPE_ITEM_12 = 12
        val TYPE_ITEM_13 = 13
        val TYPE_ITEM_14 = 14
        val TYPE_ITEM_15 = 15
        val TYPE_ITEM_16 = 16
        val TYPE_ITEM_17 = 17
        val TYPE_ITEM_18 = 18
        val TYPE_ITEM_19 = 19
        val TYPE_ITEM_20 = 20
        val TYPE_ITEM_31 = 31
        val TYPE_ITEM_32 = 32
        val TYPE_ITEM_33 = 33
        val TYPE_ITEM_34 = 34
        val TYPE_ITEM_35 = 35
        val TYPE_ITEM_36 = 36
        val TYPE_ITEM_37 = 37
        val TYPE_ITEM_38 = 38
        val TYPE_ITEM_39 = 39
        val TYPE_ITEM_40 = 40
        val TYPE_ITEM_51 = 51
        val TYPE_ITEM_52 = 52
        val TYPE_ITEM_53 = 53
        val TYPE_ITEM_54 = 54
        val TYPE_ITEM_55 = 55
        val TYPE_ITEM_56 = 56
        val TYPE_ITEM_57 = 57
        val TYPE_ITEM_58 = 58
        val TYPE_ITEM_59 = 59
        val TYPE_ITEM_60 = 60
        val TYPE_ITEM_61 = 61
        val TYPE_ITEM_62 = 62
        val TYPE_ITEM_63 = 63
        val TYPE_ITEM_64 = 64
        val TYPE_ITEM_65 = 65
        val TYPE_ITEM_66 = 66
        val TYPE_ITEM_67 = 67
        val TYPE_ITEM_68 = 68
        val TYPE_ITEM_69 = 69
        val TYPE_ITEM_70 = 70
        val TYPE_ITEM_71 = 71
        val TYPE_ITEM_72 = 72
        val TYPE_ITEM_73 = 73
        val TYPE_ITEM_74 = 74
        val TYPE_ITEM_75 = 75
        val TYPE_ITEM_76 = 76
        val TYPE_ITEM_77 = 77
        val TYPE_ITEM_78 = 78
        val TYPE_ITEM_79 = 79
        val TYPE_ITEM_80 = 80
        val TYPE_ITEM_81 = 81
        val TYPE_ITEM_82 = 82
        val TYPE_ITEM_83 = 83
        val TYPE_ITEM_84 = 84
        val TYPE_ITEM_85 = 85
        val TYPE_ITEM_86 = 86
        val TYPE_ITEM_87 = 87
        val TYPE_ITEM_88 = 88
        val TYPE_ITEM_89 = 89
        val TYPE_ITEM_90 = 90
        val TYPE_ITEM_91 = 91
        val TYPE_ITEM_92 = 92
        val TYPE_ITEM_93 = 93
        val TYPE_ITEM_94 = 94
        val TYPE_ITEM_95 = 95
        val TYPE_ITEM_96 = 96
        val TYPE_ITEM_97 = 97
        val TYPE_ITEM_98 = 98
        val TYPE_ITEM_99 = 99
        val TYPE_ITEM_100 = 100
    }

}