import React from "react"
import cssModules from "react-css-modules"
import style from "./catebar.style.scss"

@cssModules(style, {errorWhenNotFound: false})
export class CateBarElement extends React.Component {
  render() {
    let { path, header } = this.props

    return (
      <div styleName="cate-bar__element">
        <section styleName="block__content--showed">
          <a href={path} styleName="cate-bar__element__title">{header}</a>
        </section>
      </div>
    )
  }
}

CateBarElement.propTypes = {
  path: React.PropTypes.string.isRequired,
  header: React.PropTypes.string.isRequired
}

CateBarElement.defaultProps = {
  path: "#"
}