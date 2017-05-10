import React from "react"
import cssModules from "react-css-modules"
import style from "./catebar.style.scss"

@cssModules(style, {errorWhenNotFound: false})
export class CateBarElement extends React.Component {
    render() {
        return (
          <div styleName="cate-bar__element">
              <section styleName="block__content--showed">
                  <a href="#" styleName="cate-bar__element__title">
                      {this.props.header}
                  </a>
                  <span className="glyphicon glyphicon-fullscreen" styleName="cate-bar__element__icon"/>
              </section>
{/*              <section styleName="block__content--dismissed">
                  <a href="#" styleName="cate-bar__element__title">
                      {this.props.header}
                  </a>
                  <span className="glyphicon glyphicon-chevron-right" styleName="cate-bar__element__icon"/>
              </section>*/}
          </div>
        )
    }
}

CateBarElement.propTypes = {
    header: React.PropTypes.string.isRequired
}