'use strict';

//Just scroll even if there isn't content that needs to be scrolled 🤗



window.addEventListener('mousewheel', function (event) {
  var header = document.getElementById('header');
  var delta = (event.wheelDelta + 3) * -1;
  animate(delta > 0, delta);
});

var animate = function animate(check, delta) {
  var MIN_HEIGHT = 80;
  var HEIGHT = 150;
  var MAX_ZOOM = 3;
  var MAX_BLUR = 3;
  if (check) {
    var blur = 1 + delta / 150 < MAX_BLUR ? Math.ceil(1 + delta / 150) : MAX_BLUR;
    var height = HEIGHT - delta / 10 > MIN_HEIGHT ? Math.ceil(HEIGHT - delta / 10) : MIN_HEIGHT;
    var zoom = 1 + delta / 200 <= MAX_ZOOM ? 1 + delta / 200 : MAX_ZOOM;
    requestAnimationFrame(transform(header, blur, height, zoom));
  } else requestAnimationFrame(transform(header, 0, 150, 1));
};

function transform(element, blur, height, zoom) {
  element.style.filter = 'blur(' + blur + 'px)';
  element.style.height = height + 'px';
  element.style.transform = 'scale(' + zoom + ',' + zoom + ')';
};