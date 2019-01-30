;(function($) {
    // working
    var AutoSlider = function() {
        var _ = this;

       
        _.options = {
            speed: 500,
            delay: 3000,
            dots: false, 
            stay: false,
            fluid: false
        };

        _.init = function(el, options) {
            _.options = $.extend(_.options, options);

            _.el = el;
            _.ul = _.el.children('ul');
            _.li = _.ul.children('li');
            _.max = {
                width: 0,
                height: 0
            }; 

            //  Cached vars
            var o = _.options,
                ul = _.ul,
                li = _.li,
                len = li.length;

            li.each(function() {
                $(this).css('display', 'none');
                var chl = $(this).first(),
                    width = chl.outerWidth(),
                    height = chl.outerHeight();

                if (width > _.max.width) {
                    _.max.width = width;
                };
                if (height > _.max.height) {
                    _.max.height = height;
                };
            });

            _.imgIndex = 0; 

            
            if (o.fluid) {
                li.find('*').width('100%');
            }else{
                el.width(_.max.width);
                el.height(_.max.height);
            };

            o.dots && _.addDots();

            o.stay && _.stay();

            clearInterval(_.timer);
            if (len === 1) {
                li.eq(0).show();
                return;
            } else {
                li.eq(0).show();
                _.fnPlay();
            };

            return _;
        };

        _.fnPlay = function() {
            _.timer = setInterval(function() {
                _.fnAutoTab();
            }, _.options.delay);
        };

        _.fnStop = function() {
            clearInterval(_.timer);
        };

        _.fnAutoTab = function() {
            var o = _.options,
                el = _.el,
                li = _.li,
                len = li.length;

            if (_.imgIndex === len - 1) {
                _.imgIndex = 0;
            } else {
                _.imgIndex++;
            };

            li.eq(_.imgIndex).fadeIn(o.speed).siblings().hide();

            el.find('.dot').eq(_.imgIndex).addClass('active').siblings().removeClass('active');
        };

        _.stay = function() {
            var li = _.li;

            li.mouseover(function() {
                _.fnStop();
            }).mouseout(function() {
                _.fnPlay();
            });
        };

        _.addDots = function(html) {
            var el = _.el,
                li = _.li,
                len = li.length;

            html = '<ol class="dots">';

            var num = 0;
            $.each(li, function() {
                html += '<li class="dot">' + ++num + '</li>';
            });

            html += '</ol>';

            el.addClass('has-dots').append(html);

            el.find('.dot').eq(0).addClass('active');

            el.find('.dot').mouseover(function() {
                _.fnNav($(this));
            }).mouseout(function() {
                _.fnPlay();
            });
        };

        _.fnNav = function(oOlLi) {
            var o = _.options,
                el = _.el,
                li = _.li,
                len = li.length;

            _.fnStop();

            if (li.eq(_.imgIndex).is(':animated')) {
                li.eq(_.imgIndex).stop(true, true).fadeIn();
            };

            var _index = oOlLi.index();

            _.imgIndex = _index;

            li.eq(_index).fadeIn(o.speed).siblings().hide();
            el.find('.dot').eq(_index).addClass('active').siblings().removeClass('active');
        };
    };

    $.fn.autoSlider = function(options) {
        var len = this.length;

        return this.each(function() {
            var me = $(this); 

            var instance = (new AutoSlider).init(me, options);
        });
    };

    AutoSlider.version = "1.0.0";
})(jQuery);